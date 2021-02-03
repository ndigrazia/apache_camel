package org.mycompany.exception;

/**
 * A runtime exception indicating a resource unauthorized.
 */
@SuppressWarnings("serial")
public class ResourceUnAuthorizedException extends RuntimeException {
 
	public ResourceUnAuthorizedException() {
        super();
    }
    
	public ResourceUnAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
    
	public ResourceUnAuthorizedException(String message) {
        super(message);
    }
    
	public ResourceUnAuthorizedException(Throwable cause) {
        super(cause);
    }
	
}