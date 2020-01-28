package org.perfectglue.config.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"clientName",
"project"
})
public class Client {

@JsonProperty("clientName")
private String clientName;
@JsonProperty("project")
private List<Project> project = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("clientName")
public String getClientName() {
return clientName;
}

@JsonProperty("clientName")
public void setClientName(String clientName) {
this.clientName = clientName;
}

@JsonProperty("project")
public List<Project> getProject() {
return project;
}

@JsonProperty("project")
public void setProject(List<Project> project) {
this.project = project;
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