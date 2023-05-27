package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Address;
import example.test.springbootmultidb.contacts.model.AddressDTO;

public interface AddressMapper {

	AddressDTO entityToModel(Address entity);

	Address modelToEntity(AddressDTO model);
}
