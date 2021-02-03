package org.mycompany.bean;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.mycompany.model.CharacterPojo;
import org.mycompany.util.HeaderHelper;

/**
 * A bean to process the request HTTP POST method
 */
public class CreateRequestProccessBean {

	private static final Logger LOG = LoggerFactory.getLogger(CreateRequestProccessBean.class);
	
	public void process(Exchange ex, @Body CharacterPojo c) {
		LOG.info("Received request to process: {}", c);
		
		c.setId("0teUVBC5WU");
		ex.getOut().setBody(c);
		
		ex.getOut().setHeaders(HeaderHelper.addHttpResponseCodeToHeaders(
				 ex.getIn().getHeaders(), HttpStatus.CREATED.value()));
	}
	
}
