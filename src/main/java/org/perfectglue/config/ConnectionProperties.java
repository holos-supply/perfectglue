package org.perfectglue.config;

import org.perfectglue.config.model.JiraProperties;
import org.perfectglue.config.model.XrayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * The <code>ConnectionProperties</code> is a configuration settings class automatically
 * initialised and used by Spring Boot. It gets data from application.yml file in 
 * <code>main/resources</code>
 * <p/>
 * 
 * @author Willm T&uuml;ting
 *
 */
@Component
@ConfigurationProperties("connection")
public class ConnectionProperties {
	
	
	    private JiraProperties jira;
	    private XrayProperties xray;
	    
		public JiraProperties getJira() {
			return jira;
		}
		public void setJira(JiraProperties jira) {
			this.jira = jira;
		}
		public XrayProperties getXray() {
			return xray;
		}
		public void setXray(XrayProperties xray) {
			this.xray = xray;
		}
		
		@Override
		public String toString() {
			return "ConnectionProperties [jira=" + jira + ", xray=" + xray + "]";
		}

	}
