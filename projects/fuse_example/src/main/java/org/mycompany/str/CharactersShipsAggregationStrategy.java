package org.mycompany.str;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.mycompany.model.CharacterPojo;
import org.mycompany.model.StarShipPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class CharactersShipsAggregationStrategy implements AggregationStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(CharactersShipsAggregationStrategy.class);
	
    public Exchange aggregate(Exchange original, Exchange resource) {
    	List<CharacterPojo> originalBody = 
    			(List<CharacterPojo>) original.getIn().getBody();
    	Map<String, List<StarShipPojo>> resourceResponse = 
    			(Map<String, List<StarShipPojo>>) resource.getIn().getBody();
        
    	for(CharacterPojo c: originalBody)
    		c.setStarships(resourceResponse.get(c.getId()));
    	
    	LOG.info("Sending response : {}", originalBody);
    	
    	original.getOut().setBody(originalBody);
    	
        return  original;
    }

}
