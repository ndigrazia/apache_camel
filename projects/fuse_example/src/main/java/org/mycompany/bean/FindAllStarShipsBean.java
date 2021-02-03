package org.mycompany.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.mycompany.model.StarShipPojo;
import org.mycompany.util.HeaderHelper;

public class FindAllStarShipsBean {

	private static final Logger LOG = LoggerFactory.getLogger(FindAllStarShipsBean.class);
	
	public void process(Exchange ex) {
		 Map<String, List<StarShipPojo>> characters_ships = new HashMap<String, List<StarShipPojo>>();
		 
		 List<StarShipPojo> ships = new ArrayList<StarShipPojo>();
		 ships.add(new StarShipPojo("TIE Advanced x1", "Twin Ion Engine Advanced x1"));
		 characters_ships.put("0teUVBC5WU", ships);

		 ships = new ArrayList<StarShipPojo>();
		 ships.add(new StarShipPojo("Imperial shuttle", "Lambda-class T-4a shuttle"));
		 ships.add(new StarShipPojo("Millennium Falcon", "YT-1300 light freighter"));
		 characters_ships.put("26ro24HNce", ships);

		 ships = new ArrayList<StarShipPojo>();
		 ships.add(new StarShipPojo("Jedi starfighter", "Delta-7 Aethersprite-class interceptor"));
		 characters_ships.put("nxpAPnATEb", ships);

		 
		 LOG.info("Sending response : {}", characters_ships);
		 
		 ex.getOut().setBody(characters_ships);
		 
		 ex.getOut().setHeaders(HeaderHelper.addHttpResponseCodeToHeaders(
				 ex.getIn().getHeaders(), HttpStatus.OK.value()));
	}
	
}
