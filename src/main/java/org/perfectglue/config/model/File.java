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
@JsonPropertyOrder({
"fileName",
"commitMessage"
})
public class File {

@JsonProperty("fileName")
private String fileName;
@JsonProperty("commitMessage")
private String commitMessage;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("fileName")
public String getFileName() {
return fileName;
}

@JsonProperty("fileName")
public void setFileName(String fileName) {
this.fileName = fileName;
}

@JsonProperty("commitMessage")
public String getCommitMessage() {
return commitMessage;
}

@JsonProperty("commitMessage")
public void setCommitMessage(String commitMessage) {
this.commitMessage = commitMessage;
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