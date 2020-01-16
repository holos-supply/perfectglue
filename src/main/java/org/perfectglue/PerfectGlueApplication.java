package org.perfectglue;

import org.perfectglue.config.ConnectionProperties;
import org.perfectglue.config.QueueProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * The <code>PerfectGlueApplication</code> is the starter class for the entire framework
 * Starting it will initialise the program and add listeners to the port 8080
 * 
 */
@SpringBootApplication
public class PerfectGlueApplication {
	
	@Autowired
	private ConnectionProperties properties;

	public static void main(String[] args) {
		SpringApplication.run(PerfectGlueApplication.class, args);

	}

}
