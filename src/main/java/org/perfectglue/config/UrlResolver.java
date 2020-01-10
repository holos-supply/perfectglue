package org.perfectglue.config;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.perfectglue.config.model.URLChild;
import org.perfectglue.config.model.URLParent;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"parents",
"childs"
})
public class UrlResolver {

@JsonProperty("parents")
private List<URLParent> parents = null;
@JsonProperty("childs")
private List<URLChild> childs = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("parents")
public List<URLParent> getParents() {
return parents;
}

@JsonProperty("parents")
public void setParents(List<URLParent> parents) {
this.parents = parents;
}

@JsonProperty("childs")
public List<URLChild> getChilds() {
return childs;
}

@JsonProperty("childs")
public void setChilds(List<URLChild> childs) {
this.childs = childs;
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
return "parents:" + parents.toString() + "\nchilds:" + childs.toString();
}

}