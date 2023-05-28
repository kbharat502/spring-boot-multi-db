package example.test.springbootmultidb.messages.db.repos;

import example.test.springbootmultidb.messages.db.entities.ValidationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValidationMessagesRepository extends JpaRepository<ValidationMessage, Long>, JpaSpecificationExecutor<ValidationMessage> {

    public List<ValidationMessage> findByKey(String key);

    public List<ValidationMessage> findByAppNameIn(List<String> appName);
}
