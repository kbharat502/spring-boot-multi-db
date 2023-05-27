package example.test.springbootmultidb.contacts.db.entities;

import example.test.springbootmultidb.contacts.db.entities.converters.PhoneTypeConverter;
import example.test.springbootmultidb.contacts.db.entities.enums.PhoneType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import java.math.BigDecimal;


/**
 * The persistent class for the phone database table.
 * 
 */
@Entity
@Table(name="phone")
@NamedQuery(name="Phone.findAll", query="SELECT p FROM Phone p")
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_generator")
	@SequenceGenerator(name="phone_generator", sequenceName = "phone_seq", allocationSize=1)
	@Column(name="phone_id", unique=true, nullable=false, precision=131089)
	private long phoneId;

	@Column(name = "phone_no", nullable=false, precision=131089)
	private BigDecimal number;

	@Convert(converter = PhoneTypeConverter.class)
	@Column(name = "phone_type", nullable=false, length=10)
    PhoneType type;

	//bi-directional many-to-one association to Contact
	@ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="contact_id", nullable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@LazyGroup("contact")
	private Contact contact;

	public Phone() {
	}

	public long getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(long phoneId) {
		this.phoneId = phoneId;
	}

	public BigDecimal getNumber() {
		return this.number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public PhoneType getType() {
		return this.type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}