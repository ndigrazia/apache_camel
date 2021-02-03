package com.example.demo;

import org.apache.camel.Exchange;

public class Bean {
    
    public ResponseType sayHello(Exchange exchange) {
        Object id = exchange.getIn().getHeader("id");
        return new ResponseType("hello World!, " + String.valueOf(id));
    }

}
