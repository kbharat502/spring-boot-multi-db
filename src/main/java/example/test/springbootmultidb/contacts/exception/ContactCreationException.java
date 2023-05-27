package example.test.springbootmultidb.contacts.exception;

public class ContactCreationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public ContactCreationException(String message) {
		super(message);
	}
}
