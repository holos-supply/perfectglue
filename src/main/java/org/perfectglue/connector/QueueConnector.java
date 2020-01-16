package org.perfectglue.connector;

import java.util.Enumeration;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.TextMessage;

import org.perfectglue.config.QueueProperties;
import org.perfectglue.util.DataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

/**
 * The <code>QueueConnector</code> is a connector class used to connect to various queues 
 * to get messages from and post to queues (IBM MQ and RabbitMQ compatible)
 * 
 * @author Rok Pu&#154;nik
 * @author Bojan Ivanovi&#263;
 */

public class QueueConnector {

	
	Logger logger = LoggerFactory.getLogger(QueueConnector.class);
	 
	
	
	// Create variables for the connection to MQ
	QueueProperties props = DataHandler.initializeQueuePrefs();
	
	String QUEUE_NAME;
	String messageId;
	JmsFactoryFactory ff;
	JmsConnectionFactory cf;
	JMSContext context;
	Destination destination;
	
	
	public QueueConnector(String queueName) {
		QUEUE_NAME = queueName;
		
		try {
			ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			cf = ff.createConnectionFactory();
			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, props.getQueue().getHost());
			cf.setIntProperty(WMQConstants.WMQ_PORT, props.getQueue().getPort());
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, props.getQueue().getChannel());
			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, props.getQueue().getQmgr());
			cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "TestPortalSimulation");
			cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
			cf.setStringProperty(WMQConstants.USERID, props.getQueue().getApp_user());
			cf.setStringProperty(WMQConstants.PASSWORD, props.getQueue().getApp_password());
			context = cf.createContext();
			destination = context.createQueue("queue:///" + QUEUE_NAME);
			logger.info("Created connector for Queue: " + QUEUE_NAME);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void postMessage(String content) {
		TextMessage message = context.createTextMessage(content);
		JMSProducer producer = context.createProducer();
		producer.send(destination, message);
		context.close();
		logger.trace("Message got posted: "+content);
	}

	public String getFirstMessage() {
		JMSConsumer consumer = context.createConsumer(destination);
		String returnMessage = consumer.receiveBody(String.class, 5000); //delay in miliseconds
		context.close();
		return returnMessage;
	}

	public String getNotificationMessage(String va_id) {
		String returnMessage = "Zonk";
		QueueBrowser browser = context.createBrowser(context.createQueue(QUEUE_NAME));
		boolean messageFound = false;
		
		logger.debug("QueueBrowser created.");
		
		try {

			Enumeration enums = browser.getEnumeration();
			messageId = new String();
			logger.debug("Queue contains Messages? "+enums.hasMoreElements());

			while (enums.hasMoreElements()) {
				String xml = null;
				try {
				Message message = (Message) enums.nextElement();
				messageId = message.getJMSMessageID();

				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode json = objectMapper.readValue(message.getBody(String.class), JsonNode.class);

				xml = json.get("preAgreementXml").asText();
				} catch(JsonParseException e) {
					logger.info("FAULTY MESSAGE FOUND!");
					logger.trace("EXCEPTION: "+e.toString());
				}

				if (xml.contains(va_id)) {
					messageFound = true;
					logger.debug("Message was found");
					break;
				}
				logger.debug("Message wasn't found");

			}
			if (messageFound) {
			JMSConsumer consumer = context.createConsumer(destination, "JMSMessageID='" + messageId + "'");
			returnMessage = consumer.receiveBody(String.class);
			context.close();
			logger.trace("MeSSAGE"+returnMessage);
			}
			else returnMessage = "zonk";
		} catch (Exception e) {
			logger.debug("READING MESSAGE FAILED.");;
			//System.out.println(e.toString());
		}
		return returnMessage;
	}
	
	public boolean identifyNotificationMessage(String va_id) {
		QueueBrowser browser = context.createBrowser(context.createQueue(QUEUE_NAME));
		boolean messageFound = false;

		try {

			Enumeration enums = browser.getEnumeration();
			messageId = new String();

			while (enums.hasMoreElements()) {

				Message message = (Message) enums.nextElement();
				messageId = message.getJMSMessageID();

				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode json = objectMapper.readValue(message.getBody(String.class), JsonNode.class);

				String xml = json.get("preAgreementXml").asText();

				if (xml.contains(va_id)) {
					messageFound = true;
					break;
				}

			}
			context.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return messageFound;
	}
}