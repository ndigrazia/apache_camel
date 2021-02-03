package org.mycompany.bean;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.mycompany.model.CharacterPojo;
import org.mycompany.model.StarShipPojo;
import org.mycompany.util.HeaderHelper;

/**
 * A bean to process the request HTTP GET method
 */
public class GetDarthVaderResponseBean {

	private static final Logger LOG = LoggerFactory.getLogger(GetDarthVaderResponseBean.class);
	
	public void process(Exchange ex) throws Exception {
		CharacterPojo character = new CharacterPojo("0teUVBC5WU", "Darth Vader", 202, "Tatooine");
		character.addStarship(new StarShipPojo("TIE Advanced x1", "Twin Ion Engine Advanced x1"));
		
		LOG.info("Sending response : {}", character);
		
		ex.getOut().setBody(character);
		 
		ex.getOut().setHeaders(HeaderHelper.addHttpResponseCodeToHeaders(
				ex.getIn().getHeaders(), HttpStatus.OK.value()));
	}
	
}
