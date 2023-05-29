package example.test.springbootmultidb.messages.db;

import example.test.springbootmultidb.messages.service.MessagesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.AbstractMessageSource;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Log4j2
public class DBResourceBundleMessageSource extends  AbstractMessageSource {

    private Map<String, Map<String, String>> validationMessages = new HashMap<>();
    private MessagesService messagesService;

    public DBResourceBundleMessageSource(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return messagesService.getMessageForKey(code, locale);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {

        return messagesService.getMessageFormatForKey(code, locale);
    }
}
