package org.perfectglue.config;

import java.util.HashMap;
import java.util.Map;

import org.perfectglue.config.model.QueueProp;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@ConfigurationProperties("queue")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueProperties {
	@JsonProperty("queue")
	private QueueProp queue;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("queue")
	public QueueProp getQueue() {
		return queue;
	}
	
	@JsonProperty("queue")
	public void setQueue(QueueProp queue) {
		this.queue = queue;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}