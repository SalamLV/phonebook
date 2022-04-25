package com.spb.rest.mytask.phonebook.controller;

import com.spb.rest.mytask.phonebook.dao.PersonRepository;
import com.spb.rest.mytask.phonebook.error.CustomValidationException;
import com.spb.rest.mytask.phonebook.error.InstanceNotFoundException;
import com.spb.rest.mytask.phonebook.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "Person")
public class PersonController {

    private final PersonRepository personRepository;

   public PersonController(PersonRepository repository) {
        this.personRepository = repository;
    }

    // All persons
    @GetMapping("/persons")
    @ApiOperation(value = "Get all persons")
    List<Person> all() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    @ApiOperation(value = "Get one person by ID")
    Person one(@PathVariable Long id) {

        return personRepository.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException(id));
    }

    @PostMapping("/persons")
    @ApiOperation(value = "Create new person")
    Person newEmployee(@RequestBody @Valid Person newPerson) {
       // Custom validations
/*        Date curDate = new Date();
        if (newPerson.getBirthday().compareTo(curDate) > 0) {
            throw new CustomValidationException("The birth date should be in past");
        }*/

        return personRepository.save(newPerson);
    }

    @PutMapping("/persons/{id}")
    @ApiOperation(value = "Update one person by ID")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {

        return personRepository.findById(id)
                .map(person -> {
                    person.setFirst_name(newPerson.getFirst_name());
                    person.setLast_name(newPerson.getLast_name());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }

    @DeleteMapping("/persons/{id}")
    @ApiOperation(value = "Delete one person by ID")
    void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

}
