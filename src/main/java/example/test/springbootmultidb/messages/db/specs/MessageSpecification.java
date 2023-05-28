package example.test.springbootmultidb.messages.db.specs;

import example.test.springbootmultidb.messages.db.entities.Locale_;
import example.test.springbootmultidb.messages.db.entities.ValidationMessage;
import example.test.springbootmultidb.messages.db.entities.ValidationMessage_;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public class MessageSpecification {

    public static Specification<ValidationMessage> findByKey(String key) {
        return (root, query, cb) ->
            cb.equal(root.get(ValidationMessage_.key), key);
    }

    public static Specification<ValidationMessage> findByAppName(String appName) {
        return (root, query, cb) ->
                cb.equal(root.get(ValidationMessage_.appName), appName);
    }

    public static Specification<ValidationMessage> hasLocale(Locale locale) {

        return (root, query, cb) ->{
            Join<ValidationMessage, example.test.springbootmultidb.messages.db.entities.Locale> msgLocale = root.join(ValidationMessage_.locale, JoinType.INNER);
            Predicate language = cb.equal(msgLocale.get(Locale_.languageCode), locale.getLanguage());
            Predicate country = cb.equal(msgLocale.get(Locale_.country), locale.getCountry());
            return cb.and(language, country);
        };
    }
}
