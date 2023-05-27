package example.test.springbootmultidb.contacts.service.web.mappers.impl;

import example.test.springbootmultidb.contacts.db.entities.Address;
import example.test.springbootmultidb.contacts.db.entities.enums.AddressType;
import example.test.springbootmultidb.contacts.model.AddressDTO;
import example.test.springbootmultidb.contacts.model.enums.AddressDTOType;
import example.test.springbootmultidb.contacts.service.web.mappers.AddressMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDTO entityToModel(Address entity) {
        if ( entity == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        addressDTO.zipcode( entity.getZipCode() );
        addressDTO.street1( entity.getStreet1() );
        addressDTO.street2( entity.getStreet2() );
        addressDTO.city( entity.getCity() );
        addressDTO.state( entity.getState() );
        addressDTO.type( addressTypeToAddressDTOType( entity.getType() ) );

        return addressDTO.build();
    }

    @Override
    public Address modelToEntity(AddressDTO model) {
        if ( model == null ) {
            return null;
        }

        Address address = new Address();

        address.setZipCode( model.getZipcode() );
        address.setCity( model.getCity() );
        address.setState( model.getState() );
        address.setStreet1( model.getStreet1() );
        address.setStreet2( model.getStreet2() );
        address.setType( addressDTOTypeToAddressType( model.getType() ) );

        return address;
    }

    protected AddressDTOType addressTypeToAddressDTOType(AddressType addressType) {
        if ( addressType == null ) {
            return null;
        }

        AddressDTOType addressDTOType;

        switch ( addressType ) {
            case HOME: addressDTOType = AddressDTOType.HOME;
            break;
            case MAILING: addressDTOType = AddressDTOType.MAILING;
            break;
            case WORK: addressDTOType = AddressDTOType.WORK;
            break;
            case OTHER: addressDTOType = AddressDTOType.OTHER;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + addressType );
        }

        return addressDTOType;
    }

    protected AddressType addressDTOTypeToAddressType(AddressDTOType addressDTOType) {
        if ( addressDTOType == null ) {
            return null;
        }

        AddressType addressType;

        switch ( addressDTOType ) {
            case HOME: addressType = AddressType.HOME;
            break;
            case MAILING: addressType = AddressType.MAILING;
            break;
            case WORK: addressType = AddressType.WORK;
            break;
            case OTHER: addressType = AddressType.OTHER;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + addressDTOType );
        }

        return addressType;
    }
}
