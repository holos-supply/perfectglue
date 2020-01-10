package org.perfectglue.controller;

import org.perfectglue.config.ConnectionProperties;
import org.perfectglue.connector.XrayCloudConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhook")
public class JiraWebhookController {
	
	private static String[] defaultOptions = {
            "--glue", "stepdefs",
            "--plugin", "pretty",
            "--plugin", "json:target/report/cucumber.json",
            "--monochrome",
            "src/test/features"
    };
	
	@Autowired
	ConnectionProperties properties;
	
	@PutMapping("/{id}")
	public void startExecution(@PathVariable String id) {
		
		// the magic happens here
		XrayCloudConnector con = new XrayCloudConnector(properties.getJira().getCloud().getUser(),
				properties.getJira().getCloud().getPass(),
				properties.getJira().getCloud().getUrl());
		
		// read Xray
		con.getGherkinFromTestCloud(id);
		
		// execute test with cucumber
		try {
			cucumber.api.cli.Main.main(defaultOptions);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// public results to Xray
		con.publishResults(id);
	}

}
