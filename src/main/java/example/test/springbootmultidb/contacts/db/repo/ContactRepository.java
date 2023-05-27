package example.test.springbootmultidb.contacts.db.repo;

import example.test.springbootmultidb.contacts.db.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	/*
	 * @EntityGraph(value = "contact.address") Optional<Contact>
	 * findByFirstName(String firstName);
	 */
	
	
}
