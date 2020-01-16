package org.perfectglue.config.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * The <code>QueueProperties</code> is a model class required by <code>QueueProperties</code>
 *  
 * @author Rok Pu&#154;nik
 * @author Bojan Ivanovi&#263;
 *
 */
public class QueueProp {
	@JsonProperty("host")
	private String host;
	@JsonProperty("port")
	private int port;
	@JsonProperty("channel")
	private String channel;
	@JsonProperty("qmgr")
	private String qmgr;
	@JsonProperty("app_user")
	private String app_user;
	@JsonProperty("app_password")
	private String app_password;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("host")
	public String getHost() {
		return host;
	}

	@JsonProperty("host")
	public void setHost(String host) {
		this.host = host;
	}

	@JsonProperty("port")
	public int getPort() {
		return port;
	}

	@JsonProperty("port")
	public void setPort(int port) {
		this.port = port;
	}

	@JsonProperty("channel")
	public String getChannel() {
		return channel;
	}

	@JsonProperty("channel")
	public void setChannel(String channel) {
		this.channel = channel;
	}

	@JsonProperty("qmgr")
	public String getQmgr() {
		return qmgr;
	}

	@JsonProperty("qmgr")
	public void setQmgr(String qmgr) {
		this.qmgr = qmgr;
	}

	@JsonProperty("app_user")
	public String getApp_user() {
		return app_user;
	}

	@JsonProperty("app_user")
	public void setApp_user(String app_user) {
		this.app_user = app_user;
	}

	@JsonProperty("app_password")
	public String getApp_password() {
		return app_password;
	}

	@JsonProperty("app_password")
	public void setApp_password(String app_password) {
		this.app_password = app_password;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "QueueProperties [host=" + host + ", port=" + port + ", channel=" + channel + ", qmgr=" + qmgr
				+ ", app_user=" + app_user + ", app_password=" + app_password + "]";
	}
}