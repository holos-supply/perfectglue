package org.perfectglue.util;

import java.io.File;
import java.io.IOException;

import java.util.List;

import org.perfectglue.config.CamundaTask;
import org.perfectglue.config.QueueProperties;
import org.perfectglue.connector.CamundaConnector;
import org.perfectglue.config.GitProperties;
import org.perfectglue.config.ProjectProperties;
import org.perfectglue.config.UrlResolver;
import org.perfectglue.config.model.Projects;
import org.perfectglue.connector.GitConnector;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;

/**
 * The <code>DataHandler</code> is a utility class resolving YAML files into
 * their respective classes.
 * <p/>
 * All the methods in this class are static.
 * <p/>
 * 
 * @author Rok Pu&#154;nik
 *
 */
public class DataHandler { // potentially to be renamed

	/*
	 * initializes QueueProperties for QueueConnector to get data from yaml
	 */

	public static QueueProperties initializeQueueProperties() {
		QueueProperties properties = new QueueProperties();
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
		mapper.findAndRegisterModules();
		try {
			properties = mapper.readValue(new File("src/main/resources/application.yml"), QueueProperties.class);
		} catch (Exception e) {
			// a few things can go wrong here, theoretically
			// including IOException, JsonParseException and JsonMappingException
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


	public static List<CamundaTask> initCamundaTaskList(String endpoint) {
		List<CamundaTask> list = null;
		ObjectMapper mapper = new ObjectMapper();
		CamundaConnector cc = new CamundaConnector(endpoint);
		try {
			list = mapper.readValue(cc.getEndpointData(), new TypeReference<List<CamundaTask>>(){});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	public static GitProperties initializeGitProperties() {
		GitProperties gitprops = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
		mapper.findAndRegisterModules();
		try {
			gitprops = mapper.readValue(new File("src/main/resources/application.yml"), GitProperties.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gitprops;
	}

	public static ProjectProperties initializeProjects(String repo, String commitMessage) {
		ProjectProperties projects = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
		GitConnector gitconn = new GitConnector(repo);
		mapper.findAndRegisterModules();
		try {
			projects = mapper.readValue(gitconn.getFileContentsByCommitMessage("", commitMessage), ProjectProperties.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projects;
	}

	
	public static ProjectProperties initializeProjects() {
		ProjectProperties projects = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
		GitConnector gitconn = new GitConnector("holos-supply/yamltest"); 
		//System.out.println(gitconn.getFileContentsByCommitMessage("", "democlientlist"));
		mapper.findAndRegisterModules();
		try {
		projects = mapper.readValue(gitconn.getFileContentsByCommitMessage("", "democlientlist"), ProjectProperties.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projects;
	}
}
