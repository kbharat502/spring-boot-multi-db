package example.test.springbootmultidb.config.messagesource.impl;

import example.test.springbootmultidb.config.messagesource.ValidationMessageSource;
import example.test.springbootmultidb.messages.db.DBResourceBundleMessageSource;
import example.test.springbootmultidb.messages.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("val-db")
@Service
public class DatabaseMessageSource implements ValidationMessageSource {

    @Autowired
    private MessagesService messagesService;

    @Bean
    public MessageSource messageSource() {
        return new DBResourceBundleMessageSource(messagesService);
    }
}
