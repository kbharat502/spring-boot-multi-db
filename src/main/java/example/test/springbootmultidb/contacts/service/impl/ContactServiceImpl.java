package example.test.springbootmultidb.contacts.service.impl;

import example.test.springbootmultidb.contacts.db.entities.Address;
import example.test.springbootmultidb.contacts.db.entities.Contact;
import example.test.springbootmultidb.contacts.db.entities.Phone;
import example.test.springbootmultidb.contacts.db.repo.ContactRepository;
import example.test.springbootmultidb.contacts.exception.ContactCreationException;
import example.test.springbootmultidb.contacts.exception.ContactNotFoundException;
import example.test.springbootmultidb.contacts.model.ContactDTO;
import example.test.springbootmultidb.contacts.service.ContactService;
import example.test.springbootmultidb.contacts.service.web.mappers.ContactMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

	private ContactRepository contactRepo;
	
	private ContactMapper contactMapper;
	
	public ContactServiceImpl(ContactRepository contactRepo, ContactMapper contactMapper) {
		this.contactRepo = contactRepo;
		this.contactMapper = contactMapper;
	}
	
	@Override
	@Transactional
	public List<ContactDTO> getAllContact(boolean withPhones) {
		List<Contact> findAll = this.contactRepo.findAll();
		
		List<ContactDTO> contactDTOs = findAll.stream().map(Contact -> {
			
			Contact.getPhones();
			ContactDTO contact = contactMapper.entityToModel(Contact);
			
			return contact;
		}).collect(Collectors.toList());
		
		return contactDTOs;
	}

	@Override
	@Transactional
	public ContactDTO getContactById(Long id) {
		ContactDTO contactDTO = null;
		Optional<Contact> findById = contactRepo.findById(id);
		if(findById.isPresent()) {
			Contact contact = findById.get();
			contactDTO = contactMapper.entityToModel(contact);
		}
		return contactDTO;
	}

	@Override
	@Transactional
	public ContactDTO addContact(ContactDTO contactDTO) throws ContactCreationException {
		
		Contact ce = contactMapper.modelToEntity(contactDTO);
		ContactDTO model = null;
		try {
			
			if(!CollectionUtils.isEmpty(ce.getPhones())) {
				
				for(Phone pe : ce.getPhones()) {
					pe.setContact(ce);
				}
			}
			
			if(!CollectionUtils.isEmpty(ce.getAddresses())) {
				for(Address addr : ce.getAddresses()) {
					addr.setContact(ce);
				}
			}
			
			Contact contact = contactRepo.save(ce);
			
			model = contactMapper.entityToModel(contact);
		} catch (DataAccessException dae) {
			throw new ContactCreationException(dae.getMessage());
		}
		
		return model;
	}

	@Override
	@Transactional(rollbackFor = {ContactNotFoundException.class})
	public void deleteContactById(Long id) throws ContactNotFoundException {
		boolean entityExists = contactRepo.existsById(id);
		
		if(entityExists) {
			contactRepo.deleteById(id);
		} else {
			throw new ContactNotFoundException("Couldn't find the contact.");
		}
		

	}

	@Override
	public ContactDTO getContactByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

}
