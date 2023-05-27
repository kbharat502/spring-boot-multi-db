package example.test.springbootmultidb.contacts.exception.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ValidationErrors {

	@JsonProperty("fieldErrors")
	private List<FieldValidationError> fieldErrors = null;
	
	@JsonProperty("requestErrors")
	private List<RequestError> requestErrors = null;

	public List<FieldValidationError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldValidationError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public List<RequestError> getRequestErrors() {
		return requestErrors;
	}

	public void setRequestErrors(List<RequestError> requestErrors) {
		this.requestErrors = requestErrors;
	}
}
