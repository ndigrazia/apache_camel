package org.mycompany.util.route;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.mycompany.exception.BadRequestException;
import org.mycompany.exception.ResourceNotFoundException;
import org.mycompany.exception.ResourceUnAuthorizedException;
import org.mycompany.exception.TimeoutException;
import org.mycompany.util.HeaderHelper;
import org.mycompany.util.error.ErrorResponse;
import org.mycompany.util.error.ErrorType;

public abstract class HandlingErrorRoute extends RouteBuilder {
	
	private static final Logger LOG = LoggerFactory.getLogger(HandlingErrorRoute.class);

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
			.handled(true)
			.id(ErrorType.INTERNAL.getReasonPhrase())
			.process(exchange -> error(ErrorType.INTERNAL, exchange))
				.id(nameProcessor(ErrorType.INTERNAL));
	
		onException(ResourceNotFoundException.class)
			.handled(true)
			.id(ErrorType.NOT_FOUND.getReasonPhrase())
			.process(exchange -> error(ErrorType.NOT_FOUND, exchange))
				.id(nameProcessor(ErrorType.NOT_FOUND));
			
		onException(ResourceUnAuthorizedException.class)
			.handled(true)
			.id(ErrorType.PERMISSION_DENIED.getReasonPhrase())
			.process(exchange -> error(ErrorType.PERMISSION_DENIED, exchange))
				.id(nameProcessor(ErrorType.PERMISSION_DENIED));
		
		onException(BadRequestException.class)
			.handled(true)
			.id(ErrorType.INVALID_ARGUMENT.getReasonPhrase())
			.process(exchange -> error(ErrorType.INVALID_ARGUMENT, exchange))
				.id(nameProcessor(ErrorType.INVALID_ARGUMENT));
			
		onException(TimeoutException.class)
			.handled(true)
			.id(ErrorType.TIMEOUT.getReasonPhrase())
			.process(exchange -> error(ErrorType.TIMEOUT, exchange))
				.id(nameProcessor(ErrorType.TIMEOUT));
	}
	
	private void error(ErrorType error, Exchange exchange){
		Message message = (exchange.getIn() != null ? 
				exchange.getIn() : exchange.getOut());
    	
    	Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT,
    			Exception.class);
		
    	LOG.error("ERROR - [BodyType: {}, Body: {}, Headers: {}, ExceptionMessage: {}, ExceptionType: {}]", 
    			message.getBody().getClass(),  message.getBody(), message.getHeaders(), 
    				cause.getMessage(), cause.getClass());
		
		exchange.getOut().setBody(new ErrorResponse(error.messageCode(), cause.getMessage()));
		
		exchange.getOut().setHeaders(HeaderHelper.addHttpResponseCodeToHeaders(
				message.getHeaders(), error.value()));
	}
	
	private String nameProcessor(ErrorType error){
		return "Process (" + error.getReasonPhrase() + ")"; 
	}
	
}
