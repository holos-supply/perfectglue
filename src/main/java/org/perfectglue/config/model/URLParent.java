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
"simpleName",
"communicationType",
"properName"
})
public class URLParent {

@JsonProperty("simpleName")
private String simpleName;
@JsonProperty("communicationType")
private String communicationType;
@JsonProperty("properName")
private String properName;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("simpleName")
public String getSimpleName() {
return simpleName;
}

@JsonProperty("simpleName")
public void setSimpleName(String simpleName) {
this.simpleName = simpleName;
}

@JsonProperty("communicationType")
public String getCommunicationType() {
return communicationType;
}

@JsonProperty("communicationType")
public void setCommunicationType(String communicationType) {
this.communicationType = communicationType;
}

@JsonProperty("properName")
public String getProperName() {
return properName;
}

@JsonProperty("properName")
public void setProperName(String properName) {
this.properName = properName;
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
return new StringBuilder().append("\n\t"+ "simpleName: "+ simpleName+"\n\t").append("communicationType: " + communicationType+"\n\t").append("properName: " + properName+"\n\t").append("additionalProperties: " + additionalProperties+"\n").toString();
}
}