package org.perfectglue.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.perfectglue.connector.GitConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.google.common.io.Files;


/**
 * The <code>MessageHandler</code> is a utility class which allows for interacting with
 * JSON and XML in a structured way
 * 
 * @author Willm T&uuml;ting
 * 
 */

public class MessageHandler {
	
	Logger logger = LoggerFactory.getLogger(MessageHandler.class);
	public String returnGitTestMessageContents(String fileName, String commitMessage) {
		String x = "zoinks!";
		GitConnector gitconn = new GitConnector();
		try {
			x = gitconn.getFileContentsByCommitMessage("", commitMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
			
	}
	
	
	public String getJsonElement(String jsonMessage, String element) {
	    try {
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	JsonNode json = objectMapper.readValue(jsonMessage, JsonNode.class);
	    	return json.get(element).asText();
		} catch( Exception e) {
			return "!zonk";
		}
	}
	
	public String setJsonElement(String jsonMessage, String parent, String element, String value) {
		
		try {
			logger.info("Entry: "+jsonMessage);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(jsonMessage);
			ObjectNode node = (ObjectNode) root;
			node.with(parent).put(element, value);
			logger.info("Reached: "+node.toString());
			
			File x = new File("");
			mapper.writer().writeValue(x,node);
			logger.info(Files.toString(x, Charset.defaultCharset()));
			
			return null;
			}catch( Exception e) {
				logger.trace(e.toString());
				return null;
			}
	}
	
	public String getXmlElement(String xmlMessage, String element) {
		
		Document doc = convertStringToDocument(xmlMessage);
		return doc.getElementsByTagName(element).item(0).getTextContent();
		
	}
		
	public void validateXml(String file) {
		Schema schema = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			document = builder.parse(new InputSource(new StringReader(file)));;
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factoryS = SchemaFactory.newInstance(language);
			schema = factoryS.newSchema(new File(file));
			Validator validator = schema.newValidator();
			validator.validate(new DOMSource(document));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try  
        {  
            builder = factory.newDocumentBuilder();  
            InputSource is = new InputSource( new StringReader( xmlStr ) );
            is.setEncoding("UTF-8");
            Document doc = builder.parse( is ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }

	public String setXmlElement(String xmlMessage, String element, String value) {
		
		Document doc = convertStringToDocument(xmlMessage);
		doc.getElementsByTagName(element).item(0).setTextContent(value);
		
		return convertDocumentToString(doc);
		
		
	}
	/**
	 * Gets and returns only the XML part from a json Message.
	 * TODO: complete this
	 * @param jsonMessage
	 * @param xmlContainingTag
	 * @return
	 */
	public String unwrapXmlFromJson(String jsonMessage, String xmlContainingTag) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode json;
		try {
			json = objectMapper.readValue(jsonMessage, JsonNode.class);
			return  json.get(xmlContainingTag).asText();
		} catch (Exception e) {
			return null;
		}
		
	}
}
