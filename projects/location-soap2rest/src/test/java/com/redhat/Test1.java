package com.redhat;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.apache.camel.test.spring.CamelSpringBootRunner;  

import org.apache.camel.ProducerTemplate;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = Application.class,
    // turn on web during test on the defined 8080 port
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Test1 {
    
    @Autowired
    private ProducerTemplate template;

    @Test
	public void contextLoads() {
        System.out.println("**************************************");
        String json = "{\"partName\":\"motor\",\"amount\":1,\"customerName\":\"honda\"}";
		Object id = template.requestBody("http://localhost:8080/camel/hello?httpMethod=GET", json, String.class);
        System.out.println("**************************************");
    }
    
}