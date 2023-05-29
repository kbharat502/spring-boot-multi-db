package example.test.springbootmultidb.config.messagesource.impl;

import example.test.springbootmultidb.config.messagesource.ValidationMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Profile("val-prop")
@Service
public class PropertyFilesMessageSource implements ValidationMessageSource {

    private static final String DEFAULT_ENCODING = "UTF-8";
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
        rrbms.setBasenames("classpath:org/hibernate/validator/ValidationMessages","classpath:ValidationMessage");
        rrbms.setDefaultEncoding(DEFAULT_ENCODING);
        rrbms.setCacheSeconds(60);

        return rrbms;
    }
}
