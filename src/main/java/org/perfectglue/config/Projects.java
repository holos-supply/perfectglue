package org.perfectglue.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.perfectglue.config.model.Client;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "client" })
public class Projects {

	@JsonProperty("client")
	private List<Client> client = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("client")
	public List<Client> getClient() {
		return client;
	}

	@JsonProperty("client")
	public void setClient(List<Client> client) {
		this.client = client;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		System.out.println("AdditionalPropertyName: " + name);
		System.out.println("AdditionalPropertyValue: " + value.toString());
		this.additionalProperties.put(name, value);
	}
}