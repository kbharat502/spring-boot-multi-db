package example.test.springbootmultidb.contacts.service.web.mappers.impl;

import example.test.springbootmultidb.contacts.db.entities.Address;
import example.test.springbootmultidb.contacts.db.entities.Contact;
import example.test.springbootmultidb.contacts.db.entities.Phone;
import example.test.springbootmultidb.contacts.model.AddressDTO;
import example.test.springbootmultidb.contacts.model.ContactDTO;
import example.test.springbootmultidb.contacts.model.PhoneDTO;
import example.test.springbootmultidb.contacts.service.web.mappers.AddressMapper;
import example.test.springbootmultidb.contacts.service.web.mappers.ContactMapper;
import example.test.springbootmultidb.contacts.service.web.mappers.PhoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Component
public class ContactMapperImpl implements ContactMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public ContactDTO entityToModelIgnorePhones(Contact entity) {
        if ( entity == null ) {
            return null;
        }

        ContactDTO.ContactDTOBuilder contactDTO = ContactDTO.builder();

        contactDTO.id( entity.getContactId() );
        contactDTO.addresses( addressSetToAddressDTOList( entity.getAddresses() ) );
        contactDTO.email( entity.getEmail() );

        return contactDTO.build();
    }

    @Override
    public ContactDTO entityToModelIgnoreAddresses(Contact entity) {
        if ( entity == null ) {
            return null;
        }

        ContactDTO.ContactDTOBuilder contactDTO = ContactDTO.builder();

        contactDTO.id( entity.getContactId() );
        contactDTO.email( entity.getEmail() );
        contactDTO.phones( phoneSetToPhoneDTOList( entity.getPhones() ) );

        return contactDTO.build();
    }

    @Override
    public ContactDTO entityToModelIgnoreEmployee(Contact entity) {
        if ( entity == null ) {
            return null;
        }

        ContactDTO.ContactDTOBuilder contactDTO = ContactDTO.builder();

        contactDTO.id( entity.getContactId() );
        contactDTO.addresses( addressSetToAddressDTOList( entity.getAddresses() ) );
        contactDTO.email( entity.getEmail() );
        contactDTO.phones( phoneSetToPhoneDTOList( entity.getPhones() ) );

        return contactDTO.build();
    }

    @Override
    public ContactDTO entityToModelIgnoreAllChildren(Contact entity) {
        if ( entity == null ) {
            return null;
        }

        ContactDTO.ContactDTOBuilder contactDTO = ContactDTO.builder();

        contactDTO.id( entity.getContactId() );
        contactDTO.email( entity.getEmail() );

        return contactDTO.build();
    }

    @Override
    public ContactDTO entityToModel(Contact entity) {
        if ( entity == null ) {
            return null;
        }

        ContactDTO.ContactDTOBuilder contactDTO = ContactDTO.builder();

        contactDTO.id( entity.getContactId() );
        contactDTO.addresses( addressSetToAddressDTOList( entity.getAddresses() ) );
        contactDTO.email( entity.getEmail() );
        contactDTO.phones( phoneSetToPhoneDTOList( entity.getPhones() ) );

        return contactDTO.build();
    }

    @Override
    public Contact modelToEntity(ContactDTO model) {
        if ( model == null ) {
            return null;
        }

        Contact contact = new Contact();

        if ( model.getId() != null ) {
            contact.setContactId( model.getId() );
        }
        contact.setEmail( model.getEmail() );
        contact.setAddresses( addressDTOListToAddressSet( model.getAddresses() ) );
        contact.setPhones( phoneDTOListToPhoneSet( model.getPhones() ) );

        return contact;
    }

    protected List<AddressDTO> addressSetToAddressDTOList(Set<Address> set) {
        if ( set == null ) {
            return null;
        }

        List<AddressDTO> list = new ArrayList<AddressDTO>( set.size() );
        for ( Address address : set ) {
            list.add( addressMapper.entityToModel( address ) );
        }

        return list;
    }

    protected List<PhoneDTO> phoneSetToPhoneDTOList(Set<Phone> set) {
        if ( set == null ) {
            return null;
        }

        List<PhoneDTO> list = new ArrayList<PhoneDTO>( set.size() );
        for ( Phone phone : set ) {
            list.add( phoneMapper.entityToModel( phone ) );
        }

        return list;
    }

    protected Set<Address> addressDTOListToAddressSet(List<AddressDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Address> set = new LinkedHashSet<Address>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( AddressDTO addressDTO : list ) {
            set.add( addressMapper.modelToEntity( addressDTO ) );
        }

        return set;
    }

    protected Set<Phone> phoneDTOListToPhoneSet(List<PhoneDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Phone> set = new LinkedHashSet<Phone>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( PhoneDTO phoneDTO : list ) {
            set.add( phoneMapper.modelToEntity( phoneDTO ) );
        }

        return set;
    }
}
