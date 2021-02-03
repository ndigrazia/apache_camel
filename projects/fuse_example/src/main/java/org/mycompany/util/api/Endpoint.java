package org.mycompany.util.api;

import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestDefinition;
import org.springframework.http.HttpMethod;

public class Endpoint {
	
	private String path = null;
	
	private String description = null;
	
	private RestDefinition refDef = null;
	
	private RouteBuilder builder = null;
	
	public Endpoint(RouteBuilder builder, String path) {
		this(builder, path, "");
	}
	
	public Endpoint(RouteBuilder builder, String path, String description) {
		this.path = path;
		this.description = description;
		this.builder = builder;
		
		refDef = builder.rest(path)
					.description(description)
					.produces(MediaType.APPLICATION_JSON)
					.consumes(MediaType.APPLICATION_JSON)
					.skipBindingOnErrorCode(false);//Enable json marshalling for body in case of errors
	}

	public Resource createResource(String id, HttpMethod verb, String uri, String description) {
		return Resource.create(id, verb, uri, description, refDef);
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RestDefinition getRefDef() {
		return refDef;
	}

	public RouteBuilder getBuilder() {
		return builder;
	}
	
}
