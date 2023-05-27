package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Phone;
import example.test.springbootmultidb.contacts.model.PhoneDTO;

public interface PhoneMapper {
	PhoneDTO entityToModel(Phone entity);
		Phone modelToEntity(PhoneDTO phoneDTO);
}
