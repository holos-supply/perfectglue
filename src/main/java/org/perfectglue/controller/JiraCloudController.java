package org.perfectglue.controller;

import org.perfectglue.config.ConnectionProperties;
import org.perfectglue.connector.XrayCloudConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * The <code>JiraCloudController</code> is a core class used by Spring Boot to parse mappings
 * 
 * @author Willm T&uuml;ting
 *
 */
@RestController
@RequestMapping("/api/cloud")
public class JiraCloudController {

	Logger logger = LoggerFactory.getLogger(JiraCloudController.class);
	
	@Autowired
	ConnectionProperties properties;
	
	@PostMapping("/{id}")
	public void startExecution(@PathVariable String id)  {
		
		// the magic happens here
			XrayCloudConnector con = new XrayCloudConnector(properties.getXray().getCloud().getUser(),
				properties.getXray().getCloud().getPass(),
				properties.getXray().getCloud().getUrl());
			
			// read Xray
			con.getGherkinFromTestCloud(id);
			
			// execute test with cucumber
			try {
				cucumber.api.cli.Main.run(defaultOptions, Thread.currentThread().getContextClassLoader());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			logger.info("Publishing results");
			// public results to Xray
			con.publishResults(id);
	}
	
	
	private static String[] defaultOptions = {
            "--glue", "org.perfectglue.stepdefs",
            "--plugin", "pretty",
            "--plugin", "json:target/report/cucumber.json",
            "--monochrome",
            "src/test/features"
	};

}
