package org.mycompany.exception;

/**
 * A runtime exception indicating a timeout.
 */
@SuppressWarnings("serial")
public class TimeoutException extends RuntimeException {
 
	public TimeoutException() {
        super();
    }
    
	public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
    
	public TimeoutException(String message) {
        super(message);
    }
    
	public TimeoutException(Throwable cause) {
        super(cause);
    }
	
}