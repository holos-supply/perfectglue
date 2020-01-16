package org.perfectglue.config.model;

/**
 * The <code>JiraProperties</code> is a model class required by <code>ConnectionProperties</code>
 *  
 * @author Willm T&uuml;ting
 *
 */
public class JiraProperties {
	
	private CloudProperties cloud;
	private ServerProperties server;
	
	public CloudProperties getCloud() {
		return cloud;
	}
	public void setCloud(CloudProperties cloud) {
		this.cloud = cloud;
	}
	public ServerProperties getServer() {
		return server;
	}
	public void setServer(ServerProperties server) {
		this.server = server;
	}
	
	@Override
	public String toString() {
		return "JiraProperties [cloud=" + cloud + ", server=" + server + "]";
	}
	
	

}
