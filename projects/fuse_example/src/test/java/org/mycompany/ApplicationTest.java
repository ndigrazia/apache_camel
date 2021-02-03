package org.mycompany;

import static org.junit.Assert.assertNotNull;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//re-create Spring/Camel for each test
@DirtiesContext
public class ApplicationTest {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationTest.class);
	
	@Autowired
    private ProducerTemplate template;

	@Test
	public void contextLoads() {
	   LOG.info("Sending the request to find to Darth Vader");

        // use http component to find all characters
        Object id = template.requestBody("http4://localhost:8080/api/characters/", "{}", String.class);
        assertNotNull(id);

       LOG.info("Response: {}", id);
	}
	
}
