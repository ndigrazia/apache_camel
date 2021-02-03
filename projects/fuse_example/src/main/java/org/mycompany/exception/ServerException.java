package org.mycompany.exception;

/**
 * A runtime exception indicating a server exception.
 */
@SuppressWarnings("serial")
public class ServerException extends RuntimeException {
 
	public ServerException() {
        super();
    }
    
	public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
    
	public ServerException(String message) {
        super(message);
    }
    
	public ServerException(Throwable cause) {
        super(cause);
    }
	
}