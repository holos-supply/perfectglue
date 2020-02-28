package org.perfectglue.connector;

import org.camunda.connect.Connectors;
import org.camunda.connect.httpclient.HttpConnector;
import org.camunda.connect.httpclient.HttpResponse;

public class CamundaConnector {
	HttpConnector http;
	String endpoint;
	String login;
	String pass;
  public CamundaConnector(String endpoint) {
	   http = Connectors.getConnector(HttpConnector.ID);
	   this.endpoint = endpoint;
  }
  public CamundaConnector(String endpoint, String login, String pass) {
	  http = Connectors.getConnector(HttpConnector.ID);
	  this.endpoint = endpoint;
	  this.login = login;
	  this.pass = pass;
  }
  
  public String getEndpointData() {
	  HttpResponse response = http.createRequest().get().url(endpoint).execute();
	  System.out.println(response.getStatusCode());
	  return response.getResponse(); 
  }
}
