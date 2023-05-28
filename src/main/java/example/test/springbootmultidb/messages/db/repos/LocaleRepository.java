package example.test.springbootmultidb.messages.db.repos;

import example.test.springbootmultidb.messages.db.entities.Locale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaleRepository extends JpaRepository<Locale, Long>, JpaSpecificationExecutor<Locale> {
}
