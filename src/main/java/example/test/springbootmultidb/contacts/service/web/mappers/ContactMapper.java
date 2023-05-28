package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Contact;
import example.test.springbootmultidb.contacts.model.ContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AddressMapper.class, PhoneMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

	ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);
	
	@Mappings({
		@Mapping(source = "contactId", target = "id"), 
		@Mapping( target = "phones", ignore = true)
	})
	ContactDTO entityToModelIgnorePhones(Contact entity);
	
	@Mappings({
		@Mapping(source = "contactId", target = "id"), 
		@Mapping( target = "addresses", ignore = true)
	})
	ContactDTO entityToModelIgnoreAddresses(Contact entity);
	
	@Named("contactIgnoreEmployee")
	@Mappings({
		@Mapping(source = "contactId", target = "id")
	})
	ContactDTO entityToModelIgnoreEmployee(Contact entity);
	
	@Mappings({
		@Mapping(source = "contactId", target = "id"), 
		@Mapping( target = "phones", ignore = true), 
		@Mapping( target = "addresses", ignore = true)
	})
	ContactDTO entityToModelIgnoreAllChildren(Contact entity);
	
	@Named("Contact")
	@Mapping(source = "contactId", target = "id")
	ContactDTO entityToModel(Contact entity);
	
	@Mapping(source = "id", target = "contactId")
	Contact modelToEntity(ContactDTO model);
	
}
