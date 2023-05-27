package example.test.springbootmultidb.contacts.db.entities.converters;

import example.test.springbootmultidb.contacts.db.entities.enums.AddressType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AddressTypeConverter implements AttributeConverter<AddressType, String> {

	private static final String STRING_OTHER = "Other";
	private static final String STRING_MAILING = "Mailing";
	private static final String STRING_WORK = "Work";
	private static final String STRING_HOME = "Home";
	
	@Override
	public String convertToDatabaseColumn(AddressType addrType) {
		switch(addrType) {
		case HOME:
			return STRING_HOME;
		case WORK:
			return STRING_WORK;
		case MAILING:
			return STRING_MAILING;
		default: 
			return STRING_OTHER;
	}
	}

	@Override
	public AddressType convertToEntityAttribute(String dbData) {
		switch(dbData) {
		case STRING_HOME:
			return AddressType.HOME;
		case STRING_WORK:
			return AddressType.WORK;
		case STRING_MAILING:
			return AddressType.MAILING;
		default:
			return AddressType.OTHER;
		}
	}

}
