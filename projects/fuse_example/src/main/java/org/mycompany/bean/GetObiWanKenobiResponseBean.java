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
public class GetObiWanKenobiResponseBean {

	private static final Logger LOG = LoggerFactory.getLogger(GetObiWanKenobiResponseBean.class);
	
	public void process(Exchange ex) throws Exception {
		CharacterPojo character = new CharacterPojo("nxpAPnATEb", "Obi-Wan Kenobi", 182, "Stewjon");
		character.addStarship(new StarShipPojo("Jedi starfighter", "Delta-7 Aethersprite-class interceptor"));
		 
		LOG.info("Sending response : {}", character);
			
		ex.getOut().setBody(character);
		
		ex.getOut().setHeaders(HeaderHelper.addHttpResponseCodeToHeaders(
				ex.getIn().getHeaders(), HttpStatus.OK.value()));
	}
	
}
