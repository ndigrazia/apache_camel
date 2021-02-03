package org.mycompany.util.api;

import org.apache.camel.model.rest.RestDefinition;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.spi.AsEndpointUri;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.mycompany.util.LogHelper;
import org.mycompany.util.error.ErrorResponse;

public class Resource {

	private String uri = null;
	
	private String description = null;
	
	private RestDefinition refDef = null;
	
	private HttpMethod verb = null;
	
	private String id = null;
		
	private Resource(String id, HttpMethod verb, String uri, 
			String description, RestDefinition refDef) {
		this.uri = uri;
		this.description = description;
		this.refDef = refDef;
		this.verb = verb;
		this.id = id;
	}
	
	public static Resource create(String id, HttpMethod verb, String uri, 
			String description, RestDefinition refDef) {
		switch (verb) {
		  case GET:
	    	  refDef.get(uri)
	    	  	.id(id)
	    	  	.description(description);
	    	  break;
	      case POST:
	    	  refDef.post(uri)
	    	  	.id(id)
	    	  	.description(description);
	    	  break;
	      case PUT:
	    	  refDef.put(uri)
	    	  	.id(id)
	    	  	.description(description);
	    	  break;
	      case PATCH:
	    	  refDef.patch(uri)
	    	  	.id(id)
	    	  	.description(description);
	    	  break;
	      case DELETE:
	    	  refDef.delete(uri)
	    	  	.id(id)
	    	  	.description(description);
	    	  break;
		case HEAD:
			  refDef.head(uri)
			  	.id(id)
	    	  	.description(description);
			break;
		default:
			throw new RuntimeException("No Http Method: " + verb);
	    }
		
		return new Resource(id, verb, uri, description, refDef);
	}

	public void addParam(String name, RestParamType type, String desc,
			boolean isRequired, String dataType) {
		refDef.param()
			.name(name)
			.type(type)
			.description(desc)
			.required(isRequired)
			.dataType(dataType)
		.endParam();
	}

	public void addParamDefaultHeaders() {
		refDef.param()
			.name(HttpHeader.X_CORRELATOR.value())
			.description(HttpHeader.X_CORRELATOR.getReasonPhrase())
			.dataType(HttpHeader.X_CORRELATOR.dataType())
			.type(RestParamType.header)
			.required(false)
		.endParam()
		.param()
			.name(HttpHeader.X_APP_CLIENT.value())
			.description(HttpHeader.X_APP_CLIENT.getReasonPhrase())
			.dataType(HttpHeader.X_APP_CLIENT.dataType())
			.type(RestParamType.header)
			.required(false)
		.endParam();
	}
	
	public void requestModel(Class<?> model) {
		refDef.type(model);
	}
	
	public void responseModel(Class<?> model) {
		refDef.outType(model);
	}
	
	public void addDefaultErrorResponse() {
		refDef.responseMessage()
			.code(HttpStatus.BAD_REQUEST.value())
			.message(HttpStatus.BAD_REQUEST.getReasonPhrase())
			.header(HttpHeader.X_CORRELATOR.value())
				.description(HttpHeader.X_CORRELATOR.getReasonPhrase())
			.endHeader()
			.responseModel(ErrorResponse.class)
		.endResponseMessage()
		.responseMessage()
			.code(HttpStatus.UNAUTHORIZED.value())
			.message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
			.header(HttpHeader.X_CORRELATOR.value())
				.description(HttpHeader.X_CORRELATOR.getReasonPhrase())
			.endHeader()
			.responseModel(ErrorResponse.class)
		.endResponseMessage()
		.responseMessage()
			.code(HttpStatus.NOT_FOUND.value())
			.message(HttpStatus.NOT_FOUND.getReasonPhrase())
			.header(HttpHeader.X_CORRELATOR.value())
				.description(HttpHeader.X_CORRELATOR.getReasonPhrase())
			.endHeader()
			.responseModel(ErrorResponse.class)
		.endResponseMessage()
		.responseMessage()
			.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
			.header(HttpHeader.X_CORRELATOR.value())
				.description(HttpHeader.X_CORRELATOR.getReasonPhrase())
			.endHeader()
			.responseModel(ErrorResponse.class)
		.endResponseMessage()
			.responseMessage()
			.code(HttpStatus.GATEWAY_TIMEOUT.value())
			.message(HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase())
			.header(HttpHeader.X_CORRELATOR.value())
				.description(HttpHeader.X_CORRELATOR.getReasonPhrase())
			.endHeader()
			.responseModel(ErrorResponse.class)
		.endResponseMessage();
	}
	
	public void addResponse(HttpStatus status) {
		refDef.responseMessage()
			.code(status.value())
			.message(status.getReasonPhrase())
			.header(HttpHeader.X_CORRELATOR.value())
				.description(HttpHeader.X_CORRELATOR.getReasonPhrase())
			.endHeader()
		.endResponseMessage();
	}
	
	public void addResponse(HttpStatus status, Class<?> model) {
		refDef.responseMessage()
			.code(status.value())
			.message(status.getReasonPhrase())
			.responseModel(model)
			.header(HttpHeader.X_CORRELATOR.value())
				.description(HttpHeader.X_CORRELATOR.getReasonPhrase())
			.endHeader()
		.endResponseMessage();
	}	
	
	public void addRoute(@AsEndpointUri String uri) {
		refDef.route().routeId(id + "-rest")
			.bean(LogHelper.class,"logMessage(*, true)")
				.id(LogHelper.id())
			.to(uri)
				.id(id + "-endpoint")
			.bean(LogHelper.class,"logMessage(*, false)")
				.id(LogHelper.id())
		.endRest();
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String geId() {
		return id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public RestDefinition getRefDef() {
		return refDef;
	}

	public HttpMethod getVerb() {
		return verb;
	}
	
	public enum HttpHeader {
		
		X_CORRELATOR("x-correlator", "Correlation id for the different services", "string"),
		
		X_APP_CLIENT("x-app-client", "Id of the App client", "string");
		
		private final String value;

		private final String reasonPhrase;
		
		private final String dataType;

		HttpHeader(String value, String reasonPhrase, String dataType) {
			this.value = value;
			this.reasonPhrase = reasonPhrase;
			this.dataType = dataType;
		}
		
		public String value() {
			return this.value;
		}

		public String dataType() {
			return this.dataType;
		}
		
		public String getReasonPhrase() {
			return this.reasonPhrase;
		}
		
	}

}
