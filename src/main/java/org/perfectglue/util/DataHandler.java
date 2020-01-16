package org.perfectglue.util;

import java.io.File;

import org.perfectglue.config.QueueProperties;
import org.perfectglue.config.UrlResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;

/**
 * The <code>DataHandler</code> is a utility class resolving YAML files into their respective
 * classes. 
 * <p/>
 * All the methods in this class are static.
 * <p/>
 * 
 * @author Rok Pu&#154;nik
 *
 */
public class DataHandler { //potentially to be renamed

	/*
	 * initializes QueueProperties for QueueConnector to get data from yaml
	 */
	public static QueueProperties initializeQueuePrefs() {
		QueueProperties properties = new QueueProperties();
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
		mapper.findAndRegisterModules();
		try {
			properties = mapper.readValue(new File("src/main/resources/application.yml"), QueueProperties.class);
		} catch (Exception e) {
			//a few things can go wrong here, theoretically
			//including IOException, JsonParseException and JsonMappingException
			e.printStackTrace();
		}
		return properties;
	}
	
	public static UrlResolver initializeURLResolver() {
		UrlResolver resolvers = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
		mapper.findAndRegisterModules();
		try {
			resolvers = mapper.readValue(new File("src/main/resources/endpoints.yml"), UrlResolver.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resolvers;
	}
}
