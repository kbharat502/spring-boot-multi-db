package example.test.springbootmultidb.contacts.db.entities.converters;

import example.test.springbootmultidb.contacts.db.entities.enums.PhoneType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhoneTypeConverter implements AttributeConverter<PhoneType, String> {

	private static final String STRING_OTHER = "Other";
	private static final String STRING_CELL = "Cell";
	private static final String STRING_WORK = "Work";
	private static final String STRING_HOME = "Home";

	@Override
	public String convertToDatabaseColumn(PhoneType phoneType) {
		switch(phoneType) {
			case HOME:
				return STRING_HOME;
			case WORK:
				return STRING_WORK;
			case CELL:
				return STRING_CELL;
			default: 
				return STRING_OTHER;
		}
	}

	@Override
	public PhoneType convertToEntityAttribute(String dbData) {
		switch(dbData) {
		case STRING_HOME:
			return PhoneType.HOME;
		case STRING_WORK:
			return PhoneType.WORK;
		case STRING_CELL:
			return PhoneType.CELL;
		default:
			return PhoneType.OTHER;
		}
	}

}
