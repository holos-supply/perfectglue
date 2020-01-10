package org.perfectglue.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Map;

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

import org.perfectglue.config.QueueProperties;
import org.perfectglue.connector.QueueConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;
import com.google.common.io.Files;

public class MessageHandler {
	
	Logger logger = LoggerFactory.getLogger(MessageHandler.class);
	
	public String getJsonElement(String jsonMessage, String element) {
		
		try {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode json = objectMapper.readValue(jsonMessage, JsonNode.class);

		return json.get(element).asText();
		}catch( Exception e) {
			return null;
		}
	}
	
	public String setJsonElement(String jsonMessage, String parent, String element, String value) {
		
		try {
			/*ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(jsonMessage);
			ObjectNode node = (ObjectNode) root;
			node.with(parent).put(element, value);
			mapper.writer().writeValue(jsonMessage, node);*/
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
	
	/*private Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }*/
	
	public static void main(String args[]) {
		try {
		MessageHandler handle = new MessageHandler();
		//handle.logger.info("XML: "+mock.mockEasyXml().getNodeValue());
		//handle.validateXml("src/main/java/net/conology/mock/simple.xml");
		//handle.validateXml(mock.mockEasyXml2());
		//handle.validateXml(mock.mockSXml());
		
		//Document doc = convertStringToDocument(mock.mockXml());
		//System.out.println(doc.getElementsByTagName("tns:Kundenwunschtermin").item(0).getTextContent());
		
		handle.logger.info(handle.setXmlElement("", "naw:vorname", "Test"));
		
		//File file = new File("src/main/java/net/conology/mock/simple.xml"); 
		/*BufferedReader br = new BufferedReader(new FileReader(file));
		String st; 
		  while ((st = br.readLine()) != null) {
		    System.out.println(st); 
		  } */
		//String result = handle.getXmlElement(mock.mockEasyXml(), "names");
		//handle.logger.info("RESULT: "+result);
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
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
	
	public String unwrapPreagreementXmlFromJson(String jsonMessage) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode json;
		try {
			json = objectMapper.readValue(jsonMessage, JsonNode.class);
			return  json.get("preAgreementXml").asText();
		} catch (Exception e) {
			return null;
		}
		
	}
}
