package com.redhat;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class Route extends RouteBuilder {

    @Override
	public void configure() throws Exception {
        restConfiguration()
			.component("servlet")
			.bindingMode(RestBindingMode.auto)
			.dataFormatProperty("prettyPrint", "true");

            rest("/hello")
                .produces("application/json")
                .consumes("application/json")
				.get("/")
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