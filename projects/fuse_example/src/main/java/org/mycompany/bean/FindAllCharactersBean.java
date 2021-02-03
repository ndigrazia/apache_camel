package org.mycompany.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.mycompany.model.CharacterPojo;
import org.mycompany.util.HeaderHelper;

public class FindAllCharactersBean {

	private static final Logger LOG = LoggerFactory.getLogger(FindAllCharactersBean.class);
	
	public void process(Exchange ex) {
		 List<CharacterPojo> characters = new ArrayList<CharacterPojo>();
		 
		 CharacterPojo c = new CharacterPojo("0teUVBC5WU", "Darth Vader", 202, "Tatooine");
		 characters.add(c);
		 
		 c = new CharacterPojo("26ro24HNce", "Chewbacca", 228, "Kashyyyk");
		 characters.add(c);
		 
		 c= new CharacterPojo("nxpAPnATEb", "Obi-Wan Kenobi", 182, "Stewjon");
		 characters.add(c);
		
		 LOG.info("Sending response : {}", characters);
		 
		 ex.getOut().setBody(characters);
		 
		 ex.getOut().setHeaders(HeaderHelper.addHttpResponseCodeToHeaders(
				 ex.getIn().getHeaders(), HttpStatus.OK.value()));
	}
	
}
