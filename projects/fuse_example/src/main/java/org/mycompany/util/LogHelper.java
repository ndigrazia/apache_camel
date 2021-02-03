package org.mycompany.util;

import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;

/**
 * class with common helper methods taking configuration from properties
 */
@ConfigurationProperties(prefix=LogHelper.NAME)
//@Component //We register this as a component to make it a singleton and so it shows up at /configprops
public class LogHelper {

	public static final String NAME = "log-helper";
	
	private static final Logger LOG = LoggerFactory.getLogger(LogHelper.class);

    private String logHeadersPattern;

    public LogHelper() {
    }

    public void logMessage(Exchange ex, boolean isRequest){
    	Message message = (ex.getIn() != null ? ex.getIn() : ex.getOut());
    	
    	LOG.info("[{} - BodyType: {}, Body: {}, Headers: {}]", (isRequest? "REQUEST": "RESPONSE"),
    			message.getBody().getClass(), message.getBody(), message.getHeaders().entrySet().stream()
        			.filter(s -> s.getKey().matches(logHeadersPattern)).collect(Collectors.toList())); 
    }
    
    public String getLogHeadersPattern() {
        return logHeadersPattern;
    }

    public void setLogHeadersPattern(String logHeadersPattern) {
        this.logHeadersPattern = logHeadersPattern;
    }
    
    public static String id(){
   	 	return "LogMessage{" + UUID.randomUUID() + "}";
   }
   
}