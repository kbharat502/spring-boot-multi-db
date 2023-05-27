package example.test.springbootmultidb.contacts.service;

import example.test.springbootmultidb.contacts.exception.ContactCreationException;
import example.test.springbootmultidb.contacts.exception.ContactNotFoundException;
import example.test.springbootmultidb.contacts.model.ContactDTO;

import java.util.List;

public interface ContactService {

	public List<ContactDTO> getAllContact(boolean withPhones);
	
	public ContactDTO getContactById(Long id);
	
	public ContactDTO getContactByFirstName(String firstName);
	
	public ContactDTO addContact(ContactDTO contactDTO) throws ContactCreationException;
	
	public void deleteContactById(Long id) throws ContactNotFoundException;
}
