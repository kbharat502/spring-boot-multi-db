package example.test.springbootmultidb.contacts.exception.model;

import example.test.springbootmultidb.contacts.exception.handler.PreValidationError;

import java.util.List;

public class PreValidationErrors {

	private List<PreValidationError> preValidationError;
	
	public PreValidationErrors(List<PreValidationError> preValidationError) {
		this.preValidationError = preValidationError;
	}

	public List<PreValidationError> getPreValidationError() {
		return preValidationError;
	}

	public void setPreValidationError(List<PreValidationError> preValidationError) {
		this.preValidationError = preValidationError;
	}
	
}
