package org.mycompany.util.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

	@JsonProperty
	private String code;
	
	@JsonProperty
	private String message;

	public ErrorResponse() {};

	public ErrorResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		
		if (o == null || getClass() != o.getClass()) return false;

		ErrorResponse that = (ErrorResponse) o;

		return (code != null ? code.equals(that.code) : that.code == null) && 
				(message != null ? message.equals(that.message) : that.message == null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int hash = 1;
		hash = prime * hash + ((code == null) ? 0 : code.hashCode());
		hash = prime * hash  + ((message == null) ? 0 : message.hashCode());
		
		return hash;
	}

	@Override
	public String toString() {
		return "ErrorResponse { code=" + code + " , message='" + message + "' }";
	}
	
}
