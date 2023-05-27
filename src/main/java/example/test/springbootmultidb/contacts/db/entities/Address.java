package example.test.springbootmultidb.contacts.db.entities;

import example.test.springbootmultidb.contacts.db.entities.enums.AddressType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.io.Serializable;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="address")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
	@SequenceGenerator(name="address_generator", sequenceName = "address_seq", allocationSize=1)
	@Column(name="address_id", unique=true, nullable=false, precision=131089)
	private long addressId;

	@Column(nullable=false, length=50)
	private String city;

	@Column(nullable=false, length=2)
	private String state;

	@Column(nullable=false, length=100)
	private String street1;

	@Column(length=100)
	private String street2;

	@Column(name = "addr_type", nullable=false, length=20)
	private AddressType type;

	@Column(name="zip_code", nullable=false, length=5)
	private String zipCode;

	//bi-directional many-to-one association to Contact
	@ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="contact_id", nullable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@LazyGroup("contact")
	private Contact contact;

	public Address() {
	}

	public long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet1() {
		return this.street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return this.street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public AddressType getType() {
		return this.type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}