package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Phone;
import example.test.springbootmultidb.contacts.model.PhoneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhoneMapper {

	PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);
	
	//@Mapping(source = "type", target = "type", qualifiedByName = "phoneToDTO")
	PhoneDTO entityToModel(Phone entity);
	
	Phone modelToEntity(PhoneDTO phoneDTO);	
	
	
	/*
	 * @Named("phoneToDTO") public static PhoneDTOType
	 * PhoneTypeToPhoneDTOType(PhoneType phoneType) { PhoneDTOType dtoType =
	 * PhoneDTOType.OTHER;
	 * 
	 * switch(phoneType) { case HOME: dtoType = PhoneDTOType.HOME; break; case WORK:
	 * dtoType = PhoneDTOType.WORK; break; case CELL: dtoType = PhoneDTOType.CELL;
	 * break; default: dtoType = PhoneDTOType.OTHER; }
	 * 
	 * return dtoType; }
	 */
}
