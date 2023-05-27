package example.test.springbootmultidb.contacts.exception.handler;

public class PreValidationErrorImpl implements PreValidationError {

	private String elementType;
	private String element;
	private String message;
	
	public PreValidationErrorImpl(String elementType, String element, String message) {
		this.elementType = elementType;
		this.element = element;
		this.message = message;
	}
	
	
	@Override
	public String getElementType() {
		return elementType;
	}

	@Override
	public String getElement() {
		return element;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
