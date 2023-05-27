package example.test.springbootmultidb.contacts.controller;

import example.test.springbootmultidb.contacts.exception.ContactCreationException;
import example.test.springbootmultidb.contacts.exception.ContactNotFoundException;
import example.test.springbootmultidb.contacts.model.ContactDTO;
import example.test.springbootmultidb.contacts.service.ContactService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/api/contact")
public class ContactController {
	
	private static final Logger LOG = LogManager.getLogger(ContactController.class);
	
	private ContactService contactService;
	
	public ContactController(ContactService contactService) {
		this.contactService = contactService; 
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("Controller post construct...");
	}
	
		
	@GetMapping(path = "/contactId/{contactId}", produces = {MediaType.APPLICATION_JSON_VALUE}
												 /* , consumes = {"application/vnd.test.v1+json"} */)
	public ResponseEntity<ContactDTO> getContactById(@PathVariable("contactId") Long contactIdentifier) throws ContactNotFoundException {
		
		System.out.println("Contact Identifier: " + contactIdentifier);
		LOG.info("Contact Identifier: {}", contactIdentifier);
		LOG.debug("Contact Identifier: {}", contactIdentifier);
				
		ContactDTO contactDTO = contactService.getContactById(contactIdentifier);
		
		if(contactDTO == null) {
			throw new ContactNotFoundException("Could not find contact for the requested contact ID: " + contactIdentifier);
		}
		

		LOG.debug("Contact ID: {}", contactDTO.getId());
		
		return new ResponseEntity<>(contactDTO, HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ContactDTO>> getContacts() throws ContactNotFoundException {
		
		List<ContactDTO> allContact = contactService.getAllContact(true);
		
		if(CollectionUtils.isEmpty(allContact)) {
			throw new ContactNotFoundException("There are no contacts return.");
		}
		
		return new ResponseEntity<>(allContact, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )  //POST - create new contact.
	public ResponseEntity<?> handlePost(@Valid @NotNull @RequestBody ContactDTO contactDTO, HttpServletRequest request) throws ContactCreationException {
		
		ContactDTO addedContact = null;
		try {
			addedContact = contactService.addContact(contactDTO);
		} catch (DataAccessException dae) {
			throw new ContactCreationException(dae.getMessage());
		}
		
		StringBuffer requestURL = request.getRequestURL();
		LOG.debug("Request URI: {}", requestURL.toString());
		
		String contextPath = request.getContextPath();
		LOG.debug("Context Path: {}", contextPath);
		
		String domainPath = requestURL.toString().substring(0, (requestURL.toString().indexOf(contextPath) + (contextPath.length())));
		LOG.debug("Domain Path: {}", domainPath);
		
		HttpHeaders headers = new HttpHeaders();

		headers.add("Location", (domainPath + "/api/contact/contactId/" + addedContact.getId()));
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(path = "/contactId/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleDelete(@PathVariable("contactId") Long contactId) throws ContactNotFoundException {
		
		contactService.deleteContactById(contactId);
	}
	
}
