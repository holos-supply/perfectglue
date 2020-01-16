package org.perfectglue.config.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The <code>URLChild</code> is a model settings class required by <code>UrlResolver</code>
 * 
 * @author Rok Pu&#154;nik
 * @author Bojan Ivanovi&#263;
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"simpleName",
"parent",
"communicationType",
"restType",
"properName"
})
public class URLChild {

@JsonProperty("simpleName")
private String simpleName;
@JsonProperty("parent")
private String parent;
@JsonProperty("communicationType")
private String communicationType;
@JsonProperty("restType")
private int restType;
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

@JsonProperty("parent")
public String getParent() {
return parent;
}

@JsonProperty("parent")
public void setParent(String parent) {
this.parent = parent;
}

@JsonProperty("communicationType")
public String getCommunicationType() {
return communicationType;
}

@JsonProperty("communicationType")
public void setCommunicationType(String communicationType) {
this.communicationType = communicationType;
}

@JsonProperty("restType")
public int getRestType() {
return restType;
}

@JsonProperty("restType")
public void setRestType(int restType) {
this.restType = restType;
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
return new StringBuilder().append("\n\tsimpleName: " + simpleName+"\n\t").append("parent: " + parent+"\n\t").append("communicationType: " + communicationType+"\n\t").append("restType: " + restType+"\n\t").append("properName: " + properName+"\n\t").append("additionalProperties: " + additionalProperties+"\n\t").toString();
}

}