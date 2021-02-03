package org.mycompany.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.mycompany.model.CharacterPojo;
import org.mycompany.route.ApplicationRoute.Uri;
import org.mycompany.util.api.Endpoint;
import org.mycompany.util.api.Resource;

@Component
public class RestApiEndpoint extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		Endpoint endpoint = new Endpoint(this, "/characters");
		
		//Get an existing character by id
		Resource resource = endpoint.createResource("get-resource",
				HttpMethod.GET, "/{id}", "Get an existing character by id");
		resource.addParam("id", RestParamType.path, "Id of the character", true, "string");
		resource.addParamDefaultHeaders();
		resource.addResponse(HttpStatus.OK);
		resource.responseModel(CharacterPojo.class);
		resource.addDefaultErrorResponse();
		resource.addRoute(Uri.GET.value());
		
		//Find all characters
		resource = endpoint.createResource("find-all-resource",
				HttpMethod.GET, "", "Find all characters");
		resource.addParamDefaultHeaders();
		resource.addResponse(HttpStatus.OK);
		resource.responseModel(CharacterPojo[].class);
		resource.addDefaultErrorResponse();
		resource.addRoute(Uri.FIND_ALL.value());
		
		//Remove an existing character by id
		resource = endpoint.createResource("remove-resource",
				HttpMethod.DELETE, "/{id}", "Remove an existing character by id");
		resource.addParam("id", RestParamType.path, "Id of the character", true, "string");
		resource.addParamDefaultHeaders();
		resource.addResponse(HttpStatus.OK);
		resource.responseModel(CharacterPojo.class);
		resource.addDefaultErrorResponse();
		resource.addRoute(Uri.REMOVE.value());
		
		//Update an existing character by id
		resource = endpoint.createResource("update-resource",
				HttpMethod.PUT, "/{id}", "Update an existing character by id");
		resource.addParam("id", RestParamType.path, "Id of the character", true, "string");
		resource.addParamDefaultHeaders();
		resource.addResponse(HttpStatus.OK);
		resource.requestModel(CharacterPojo.class);
		resource.responseModel(CharacterPojo.class);
		resource.addDefaultErrorResponse();
		resource.addRoute(Uri.UPDATE.value());
		
		//Create a character
		resource = endpoint.createResource("create-resource",
				HttpMethod.POST, "", "Create a character");
		resource.addParamDefaultHeaders();
		resource.requestModel(CharacterPojo.class);
		resource.addResponse(HttpStatus.CREATED, CharacterPojo.class);
		resource.addDefaultErrorResponse();
		resource.addRoute(Uri.ADD.value());
	}

}
