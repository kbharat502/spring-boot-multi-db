package example.test.springbootmultidb.contacts.service.web.mappers.impl;

import example.test.springbootmultidb.contacts.db.entities.Phone;
import example.test.springbootmultidb.contacts.db.entities.enums.PhoneType;
import example.test.springbootmultidb.contacts.model.PhoneDTO;
import example.test.springbootmultidb.contacts.model.enums.PhoneDTOType;
import example.test.springbootmultidb.contacts.service.web.mappers.PhoneMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class PhoneMapperImpl implements PhoneMapper {

    @Override
    public PhoneDTO entityToModel(Phone entity) {
        if ( entity == null ) {
            return null;
        }

        PhoneDTO.PhoneDTOBuilder phoneDTO = PhoneDTO.builder();

        phoneDTO.type( phoneTypeToPhoneDTOType( entity.getType() ) );
        if ( entity.getNumber() != null ) {
            phoneDTO.number( entity.getNumber().toString() );
        }

        return phoneDTO.build();
    }

    @Override
    public Phone modelToEntity(PhoneDTO phoneDTO) {
        if ( phoneDTO == null ) {
            return null;
        }

        Phone phone = new Phone();

        if ( phoneDTO.getNumber() != null ) {
            phone.setNumber( new BigDecimal( phoneDTO.getNumber() ) );
        }
        phone.setType( phoneDTOTypeToPhoneType( phoneDTO.getType() ) );

        return phone;
    }

    protected PhoneDTOType phoneTypeToPhoneDTOType(PhoneType phoneType) {
        if ( phoneType == null ) {
            return null;
        }

        PhoneDTOType phoneDTOType;

        switch ( phoneType ) {
            case HOME: phoneDTOType = PhoneDTOType.HOME;
            break;
            case WORK: phoneDTOType = PhoneDTOType.WORK;
            break;
            case CELL: phoneDTOType = PhoneDTOType.CELL;
            break;
            case OTHER: phoneDTOType = PhoneDTOType.OTHER;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + phoneType );
        }

        return phoneDTOType;
    }

    protected PhoneType phoneDTOTypeToPhoneType(PhoneDTOType phoneDTOType) {
        if ( phoneDTOType == null ) {
            return null;
        }

        PhoneType phoneType;

        switch ( phoneDTOType ) {
            case HOME: phoneType = PhoneType.HOME;
            break;
            case WORK: phoneType = PhoneType.WORK;
            break;
            case CELL: phoneType = PhoneType.CELL;
            break;
            case OTHER: phoneType = PhoneType.OTHER;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + phoneDTOType );
        }

        return phoneType;
    }
}
