package org.mycompany.util.error;

import org.springframework.http.HttpStatus;

public enum ErrorType {

	INTERNAL(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL"),
	
	NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND"),
	
	PERMISSION_DENIED(HttpStatus.UNAUTHORIZED, "PERMISSION_DENIED"),
	
	INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "INVALID_ARGUMENT"),
	
	TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "TIMEOUT");
	
	private final HttpStatus status;

	private final String messageCode;

	ErrorType(HttpStatus status, String messageCode) {
		this.status = status;
		this.messageCode = messageCode;
	}
	
	/**
	 * Return the message code of the status code.
	 */
	public String messageCode() {
		return this.messageCode;
	}
	
	/**
	 * Return the integer value of the status code.
	 */
	public int value() {
		return this.status.value();
	}

	/**
	 * Return the reason phrase of the status code.
	 */
	public String getReasonPhrase() {
		return this.status.getReasonPhrase();
	}
	
}
