package example.test.springbootmultidb.contacts.db.specs;

import example.test.springbootmultidb.contacts.db.entities.Contact;
import example.test.springbootmultidb.contacts.db.entities.Employee;
import example.test.springbootmultidb.contacts.db.entities.Phone;
import example.test.springbootmultidb.contacts.db.entities.Contact_;
import example.test.springbootmultidb.contacts.db.entities.Employee_;
import example.test.springbootmultidb.contacts.db.entities.Phone_;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> findEmployeeOnlyByPhone(Long phoneNumber) {
        return (root, query, cb) ->{
            Join<Employee, Contact> empContact = root.join(Employee_.contact, JoinType.INNER);
            Join<Contact, Phone> contactPhone = empContact.join(Contact_.phones, JoinType.INNER);
            query.multiselect(root.get(Employee_.FIRST_NAME), root.get(Employee_.LAST_NAME),
                    root.get(Employee_.DATE_OF_BIRTH), root.get(Employee_.DEPARTMENT));
            return cb.equal(contactPhone.get(Phone_.number), phoneNumber);
        };
    }
}
