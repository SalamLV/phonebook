package com.spb.rest.phonebook.service.mapper;

import com.spb.rest.phonebook.dto.PhoneDTO;
import com.spb.rest.phonebook.entity.PhoneEntity;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {
    public PhoneDTO toDTO (PhoneEntity phoneEntity) {
        return new PhoneDTO(
                phoneEntity.getId(),
                phoneEntity.getPersonid(),
                phoneEntity.getPhone(),
                phoneEntity.getType()
        );
    }

    public PhoneEntity toEntity(PhoneDTO phoneDTO){
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(phoneDTO.id());
        phoneEntity.setPersonid(phoneDTO.personid());
        phoneEntity.setPhone(phoneDTO.phone());
        phoneEntity.setType(phoneDTO.type());
        return phoneEntity;
    }
}
