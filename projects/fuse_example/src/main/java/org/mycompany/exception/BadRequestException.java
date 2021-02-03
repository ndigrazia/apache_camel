package org.mycompany.exception;

/**
 * A runtime exception indicating a bad client request.
 */
@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {
 
	public BadRequestException() {
        super();
    }
    
	public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    
	public BadRequestException(String message) {
        super(message);
    }
    
	public BadRequestException(Throwable cause) {
        super(cause);
    }
	
}