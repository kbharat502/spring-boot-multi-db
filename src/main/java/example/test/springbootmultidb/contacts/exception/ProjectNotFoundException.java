package example.test.springbootmultidb.contacts.exception;

import example.test.springbootmultidb.contacts.exception.model.Message;

import java.util.HashSet;
import java.util.Set;

public class ProjectNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    private Set<Message> messages;

    public ProjectNotFoundException(String message) {
        super(message);

        messages = new HashSet<Message>();
        messages.add(new Message(message));
    }

    public ProjectNotFoundException(String message, Set<Message> messages) {
        super(message);

        this.messages = messages;
    }
}
