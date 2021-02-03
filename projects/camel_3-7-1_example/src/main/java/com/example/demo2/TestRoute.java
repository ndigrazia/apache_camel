package com.example.demo2;

import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class TestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
			//.component("servlet")
			//.host("localhost").port(8080)
			.bindingMode(RestBindingMode.auto)
			.dataFormatProperty("prettyPrint", "true");
			/*.apiContextPath("/swagger")	
			.apiContextRouteId("swagger")
			//and enable CORS
			.apiProperty("cors", "true");*/
		
		rest("/hello")
			//.id("id1")
			.produces("application/json")
			.consumes("application/json")
			//.skipBindingOnErrorCode(false)
				.get("/")
					//.id("id2")
					.description("Find all characters")
					 .responseMessage()
						.code(200)
						.message("ok")
					.endResponseMessage()
					.outType(String.class)
					.to("direct:remoteService");
		
		
		from("direct:remoteService")
		  .routeId("direct-route")
		  .tracing()
		  .transform().simple("Hello World!!!!!")
		  .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
		
	}

}
