package example.test.springbootmultidb.contacts.exception.handler;

public interface PreValidationError {

	String getElementType();
	
	String getElement();
	
	String getMessage();
	
}
