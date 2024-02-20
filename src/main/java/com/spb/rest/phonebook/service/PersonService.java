package com.spb.rest.phonebook.service;

import com.spb.rest.phonebook.dto.PersonDTO;
import com.spb.rest.phonebook.entity.PersonEntity;
import com.spb.rest.phonebook.error.InstanceNotFoundException;
import com.spb.rest.phonebook.repository.PersonRepository;
import com.spb.rest.phonebook.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private PersonMapper personMapper;
    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> getAllPersons () {
        List<PersonEntity> personEntities = personRepository.findAll();
        List<PersonDTO> dtos = new ArrayList<>();
        for (PersonEntity personEntity : personEntities) {
            PersonDTO dto = this.personMapper.toDTO(personEntity);
            dtos.add(dto);
        }
        return dtos;
    }

    public PersonDTO getPerson(Long id) {
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException(id));

        PersonDTO personDTO = this.personMapper.toDTO(personEntity);
        return personDTO;
    }

    public PersonDTO savePerson(PersonDTO personDTO) {
        PersonEntity person = this.personMapper.toEntity(personDTO);
        PersonEntity savedPerson = this.personRepository.save(person);
        return this.personMapper.toDTO(savedPerson);
    }

    public PersonDTO updatePerson(PersonDTO personDTO, Long id) {
        PersonEntity person = this.personRepository.findById(id)
                .map(foundPerson -> {
                    foundPerson.setFirst_name(personDTO.first_name());
                    foundPerson.setLast_name(personDTO.last_name());
                    foundPerson.setBirthday(personDTO.birthday());
                    return this.personRepository.save(foundPerson);
                })
                .orElseGet(() -> {
                    PersonEntity newPerson = this.personMapper.toEntity(personDTO);
                    newPerson.setId(id);
                    return this.personRepository.save(newPerson);
                });
        return this.personMapper.toDTO(person);
    }

    public void deletePerson(Long id) {
        this.personRepository.deleteById(id);
    }
}
