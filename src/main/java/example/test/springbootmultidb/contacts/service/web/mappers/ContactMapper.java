package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Contact;
import example.test.springbootmultidb.contacts.model.ContactDTO;

public interface ContactMapper {

    ContactDTO entityToModelIgnorePhones(Contact entity);

    ContactDTO entityToModelIgnoreAddresses(Contact entity);

    ContactDTO entityToModelIgnoreEmployee(Contact entity);

    ContactDTO entityToModelIgnoreAllChildren(Contact entity);

    ContactDTO entityToModel(Contact entity);

    Contact modelToEntity(ContactDTO model);
}
