package com.spb.rest.phonebook.service.mapper;

import com.spb.rest.phonebook.dto.PersonDTO;
import com.spb.rest.phonebook.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public PersonDTO toDTO (PersonEntity personEntity) {
        return new PersonDTO(
                personEntity.getId(),
                personEntity.getFirst_name(),
                personEntity.getLast_name(),
                personEntity.getBirthday()
        );
    }

    public PersonEntity toEntity (PersonDTO personDTO) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(personDTO.id());
        personEntity.setFirst_name(personDTO.first_name());
        personEntity.setLast_name(personDTO.last_name());
        personEntity.setBirthday(personDTO.birthday());
        return personEntity;
    }
}
