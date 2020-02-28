package org.perfectglue.config;

import java.util.ArrayList;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "links", "id", "definitionId", "businessKey", "caseInstanceId", "ended", "suspended", "tenantId" })
public class CamundaTask {

	@JsonProperty("links")
	private List<Object> links = new ArrayList<Object>();
	@JsonProperty("id")
	private String id;
	@JsonProperty("definitionId")
	private String definitionId;
	@JsonProperty("businessKey")
	private String businessKey;
	@JsonProperty("caseInstanceId")
	private Object caseInstanceId;
	@JsonProperty("ended")
	private boolean ended;
	@JsonProperty("suspended")
	private boolean suspended;
	@JsonProperty("tenantId")
	private Object tenantId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("links")
	public List<Object> getLinks() {
		return links;
	}

	@JsonProperty("links")
	public void setLinks(List<Object> links) {
		this.links = links;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("definitionId")
	public String getDefinitionId() {
		return definitionId;
	}

	@JsonProperty("definitionId")
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	@JsonProperty("businessKey")
	public String getBusinessKey() {
		return businessKey;
	}

	@JsonProperty("businessKey")
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	@JsonProperty("caseInstanceId")
	public Object getCaseInstanceId() {
		return caseInstanceId;
	}

	@JsonProperty("caseInstanceId")
	public void setCaseInstanceId(Object caseInstanceId) {
		this.caseInstanceId = caseInstanceId;
	}

	@JsonProperty("ended")
	public boolean isEnded() {
		return ended;
	}

	@JsonProperty("ended")
	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	@JsonProperty("suspended")
	public boolean isSuspended() {
		return suspended;
	}

	@JsonProperty("suspended")
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	@JsonProperty("tenantId")
	public Object getTenantId() {
		return tenantId;
	}

	@JsonProperty("tenantId")
	public void setTenantId(Object tenantId) {
		this.tenantId = tenantId;
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
		return "".concat("\nlinks: " + links).concat("\nid: " + id).concat("\ndefinitionId: " + definitionId)
				.concat("\nbusinessKey: " + businessKey).concat("\ncaseInstanceId: " + caseInstanceId).concat("\nended: " + ended)
				.concat("\nsuspended: " + suspended).concat("\ntenantId: " + tenantId);
	}

}