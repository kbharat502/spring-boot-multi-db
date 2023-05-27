package example.test.springbootmultidb.contacts.exception.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestError {

	@JsonProperty("message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
