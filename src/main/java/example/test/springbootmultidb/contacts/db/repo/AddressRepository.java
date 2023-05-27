package example.test.springbootmultidb.contacts.db.repo;

import example.test.springbootmultidb.contacts.db.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
