package org.perfectglue.config;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.perfectglue.config.model.GitHubProps;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "github" })
public class GitProperties {

	@JsonProperty("github")
	private GitHubProps github;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("github")
	public GitHubProps getGithub() {
		return github;
	}

	@JsonProperty("github")
	public void setGithub(GitHubProps github) {
		this.github = github;
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