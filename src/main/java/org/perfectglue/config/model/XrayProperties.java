package org.perfectglue.config.model;
/**
 * The <code>XrayProperties</code> is a model class required by <code>ConnectionProperties</code>
 *  
 * @author Willm T&uuml;ting
 *
 */
public class XrayProperties {
	
	private CloudProperties cloud;

	public CloudProperties getCloud() {
		return cloud;
	}

	public void setCloud(CloudProperties cloud) {
		this.cloud = cloud;
	}

	@Override
	public String toString() {
		return "XrayProperties [cloud=" + cloud + "]";
	}
	

}
