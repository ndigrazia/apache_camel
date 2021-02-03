package com.example.demo;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  
public class MyRouterConfiguration {

  @Bean
  RoutesBuilder myRouter() {
    return new RouteBuilder() {

        @Override
        public void configure() throws Exception {
            restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json);
    
            rest("/hello").get("/{id}")
                .outType(ResponseType.class)
                .to("direct:hello");
    
            from("direct:hello").to("bean:com.example.demo.Bean?method=sayHello");
               
        }

    };
  }

}