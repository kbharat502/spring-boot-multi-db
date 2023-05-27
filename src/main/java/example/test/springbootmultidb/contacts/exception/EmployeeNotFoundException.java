package example.test.springbootmultidb.contacts.exception;

import example.test.springbootmultidb.contacts.exception.model.Message;

import java.util.HashSet;
import java.util.Set;

public class EmployeeNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private Set<Message> messages;

    public EmployeeNotFoundException(String message) {
        super(message);

        messages = new HashSet<Message>();
        messages.add(new Message(message));
    }

    public EmployeeNotFoundException(String message, Set<Message> messages) {
        super(message);

        this.messages = messages;
    }
}
