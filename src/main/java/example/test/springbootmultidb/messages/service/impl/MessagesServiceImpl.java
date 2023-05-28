package example.test.springbootmultidb.messages.service.impl;

import example.test.springbootmultidb.messages.db.entities.ValidationMessage;
import example.test.springbootmultidb.messages.db.repos.ValidationMessagesRepository;
import example.test.springbootmultidb.messages.service.MessagesService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static example.test.springbootmultidb.messages.db.specs.MessageSpecification.findByKey;
import static example.test.springbootmultidb.messages.db.specs.MessageSpecification.hasLocale;

@Log4j2
@Service
@Profile("val-db")
public class MessagesServiceImpl implements MessagesService {

    private Map<String, Map<String, String>> validationMessagesMap = new HashMap<>();

    private ValidationMessagesRepository repository;

    public MessagesServiceImpl(ValidationMessagesRepository repository) {
        this.repository = repository;
    }

    /**
     * In this method after initialization of this class, we are fetching the validation messages
     * from the database and storing in a map grouping by the locale as the key and the value is a
     * map of key and value string.
     */

    @EventListener(ApplicationStartedEvent.class)
    @Scheduled(cron = "0 30 4 * * *")
    //@Scheduled(fixedRate = 60000L, initialDelay = 60000L)
    @Transactional(propagation = Propagation.REQUIRED)
    public void getAllMessages() {
        List<ValidationMessage> validationMessages = repository.findByAppNameIn(Arrays.asList("EMPDEPT", "COMMON"));

        validationMessagesMap = validationMessages.stream().collect(
                        Collectors.groupingBy(
                                vm -> String.join("_", vm.getLocale().getLanguageCode().toLowerCase(),
                                        vm.getLocale().getCountry().toUpperCase()),
                                Collectors.toList()
                        )
                ).entrySet().stream()
                .map(v -> {
                    Map<String, String> collect = v.getValue().stream().collect(
                            Collectors.toMap(vm -> vm.getKey(), vm -> vm.getValue()));
                    Object[] msgofLocale = new Object[]{v.getKey(), collect};
                    return msgofLocale;
                })
                .collect(Collectors.toMap(av -> (String) av[0], av -> (Map<String, String>) av[1]));

        log.info("Messages: {}", validationMessagesMap);
    }

    @Override
    public MessageFormat getMessageFormatForKey(String key, Locale locale) {

        String msgString = findMessageForKeyFromMap(key, locale);

        return new MessageFormat(msgString);
    }

    private String findMessageForKeyFromMap(String key, Locale locale) {
        String msgString = validationMessagesMap.get(locale.toString()).get(key);
        if(StringUtils.isBlank(msgString) && !StringUtils.equalsIgnoreCase(Locale.US.getVariant(), locale.getVariant())) {
            msgString = validationMessagesMap.get(Locale.US.getVariant()).get(key);
        }
        return msgString;
    }

    @Override
    public String getMessageForKey(String key, Locale locale) {

        String msgString = findMessageForKeyFromMap(key, locale);

        return msgString;
    }

    /**
    * Example to use spec.<br/>
     * <b></b>Note: This reads messages directly from database.</b>
    * @see "getMessageFormatForKey(String key, Locale locale)"
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageFormat getMessageForKeyUsingSpecs(String key, Locale locale) {

        //Locale locale1 = new Locale("en", "GB");
        List<ValidationMessage> validationMessageList = repository.findAll(findByKey(key).and(hasLocale(locale)));

        ValidationMessage validationMessage = validationMessageList.stream()
                .filter(vm -> (vm.getLocale().getLanguageCode().equalsIgnoreCase(locale.getLanguage())
                                && vm.getLocale().getCountry().equalsIgnoreCase(locale.getCountry())))
                .findFirst().orElse(null);

        return new MessageFormat(validationMessage.getValue());
    }
}
