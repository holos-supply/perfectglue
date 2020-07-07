package org.perfectglue.controller;

import org.perfectglue.config.ConnectionProperties;
import org.perfectglue.connector.XrayServerConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server")
public class JiraServerController {
	
	@Autowired
	ConnectionProperties properties;
	
	@PostMapping("/{id}")
	public void startExecution(@PathVariable String id)  {
		XrayServerConnector con = new XrayServerConnector(properties.getJira().getServer().getUser(),
				properties.getJira().getServer().getPass(),
				properties.getJira().getServer().getUrl());
		
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
	
	
	private static String[] defaultOptions = {
            "--glue", "org.perfectglue.stepdefs",
            "--plugin", "pretty",
            "--plugin", "json:target/report/cucumber.json",
            "--monochrome",
            "src/test/features"
	};

}
