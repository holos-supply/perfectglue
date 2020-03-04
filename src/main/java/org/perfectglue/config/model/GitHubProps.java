package org.perfectglue.config.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "url", "oauth", "testDataYMLCommit" })
public class GitHubProps {

	@JsonProperty("url")
	private String url;
	@JsonProperty("oauth")
	private String oauth;
	@JsonProperty("testDataYMLCommit")
	private String testDataYMLCommit;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("oauth")
	public String getOauth() {
		return oauth;
	}

	@JsonProperty("oauth")
	public void setOauth(String oauth) {
		this.oauth = oauth;
	}
	@JsonProperty("testDataYMLCommit")
	public String gettestDataYMLCommit() {
		return testDataYMLCommit;
	}

	@JsonProperty("testDataYMLCommit")
	public void settestDataYMLCommit(String testDataYMLCommit) {
		this.testDataYMLCommit = testDataYMLCommit;
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
