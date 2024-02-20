package com.spb.rest.phonebook.controller;

import com.spb.rest.phonebook.dto.PersonDTO;
import com.spb.rest.phonebook.repository.PersonRepository;
import com.spb.rest.phonebook.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Person")
public class PersonController {
    @Autowired
    private PersonService personService;

   public PersonController() { }

    // All persons
    @GetMapping("/persons")
    @ApiOperation(value = "Get all persons")
    List<PersonDTO> all() {
        return this.personService.getAllPersons();
    }


    @GetMapping("/persons/{id}")
    @ApiOperation(value = "Get one person by ID")
    PersonDTO one(@PathVariable Long id) {
       return this.personService.getPerson(id);
    }

    @PostMapping("/persons")
    @ApiOperation(value = "Create new person")
    PersonDTO newEmployee(@RequestBody @Valid PersonDTO newPerson) {
        return this.personService.savePerson(newPerson);
    }

    @PutMapping("/persons/{id}")
    @ApiOperation(value = "Update one person by ID")
    PersonDTO replacePerson(@RequestBody PersonDTO person, @PathVariable Long id) {
        return this.personService.updatePerson(person, id);
    }

    @DeleteMapping("/persons/{id}")
    @ApiOperation(value = "Delete one person by ID")
    void deletePerson(@PathVariable Long id) {
        this.personService.deletePerson(id);
    }

}
