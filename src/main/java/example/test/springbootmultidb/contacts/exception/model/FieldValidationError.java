package example.test.springbootmultidb.contacts.exception.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FieldValidationError {

	@JsonProperty("fieldName")
	private String fieldName;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("rejectedValue")
	private Object rejectedValue;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}
	
	
}
