package org.mycompany.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestApiConfig extends RouteBuilder {
	
	private static final String TITLE = "Swagger Star Wars Characters";
	
	private static final String VERSION = "1.0.0";
	
	private static final String DESCRIPTION = "The essential list of our favourite characters in a galaxy far, far away.... "
			+ "You can find out more about Swagger at [http://swagger.io](http://swagger.io) or"
				+ " on [irc.freenode.net, #swagger](http://swagger.io/irc/).";
	
	@Override
	public void configure() throws Exception {
		//Component with the Rest DSL 
		restConfiguration()
			.component("servlet")
			.bindingMode(RestBindingMode.json)
			.dataFormatProperty("prettyPrint", "true")
	         
			//swagger endpoint path
			.apiContextPath("/swagger")	
			.apiContextRouteId("swagger")
			.apiProperty("api.title", TITLE)
			.apiProperty("api.version", VERSION)
			.apiProperty("api.description", DESCRIPTION)
			//and enable CORS
			.apiProperty("cors", "true");
	}

}
