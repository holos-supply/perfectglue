package org.perfectglue.connector;

public interface IXrayConnector {
	
	public void getGherkinFromTestCloud(String id);
	
	public void publishResults(String id);

}
