package example.test.springbootmultidb.contacts.db.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@Table(name="contact")
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_generator")
	@SequenceGenerator(name="contact_generator", sequenceName = "contact_seq", allocationSize=1)
	@Column(name="contact_id", unique=true, nullable=false, precision=131089)
	private long contactId;

	@Column(length=100)
	private String email;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="contact", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	@LazyGroup("addresses")
	private Set<Address> addresses;

	//bi-directional many-to-one association to Phone
	@OneToMany(mappedBy="contact", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	@LazyGroup("phones")
	private Set<Phone> phones;

	//bi-directional one-to-one association to Employee
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="emp_id", nullable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@LazyGroup("employee")
	private Employee employee;

	public Contact() {
	}

	public long getContactId() {
		return this.contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setContact(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setContact(null);

		return address;
	}

	public Set<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public Phone addPhone(Phone phone) {
		getPhones().add(phone);
		phone.setContact(this);

		return phone;
	}

	public Phone removePhone(Phone phone) {
		getPhones().remove(phone);
		phone.setContact(null);

		return phone;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}