package example.test.springbootmultidb.messages.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Locale;

public interface MessagesService {

    MessageFormat getMessageFormatForKey(String key, Locale locale);

    String getMessageForKey(String key, Locale locale);

    MessageFormat getMessageForKeyUsingSpecs(String key, Locale locale);
}
