package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Address;
import example.test.springbootmultidb.contacts.model.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
	
	@Mapping(source = "zipCode", target = "zipcode")
	AddressDTO entityToModel(Address entity);
	
	@Mapping(source = "zipcode", target = "zipCode")
	Address modelToEntity(AddressDTO model);
}
