package com.spb.rest.mytask.phonebook.controller;

import com.spb.rest.mytask.phonebook.dao.PhoneRepository;
import com.spb.rest.mytask.phonebook.error.CustomValidationException;
import com.spb.rest.mytask.phonebook.error.ErrorResponse;
import com.spb.rest.mytask.phonebook.error.InstanceNotFoundException;
import com.spb.rest.mytask.phonebook.model.Phone;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Api(tags = "Phone")
public class PhoneController {

    private final PhoneRepository phoneRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

   public PhoneController(PhoneRepository repository) {
        this.phoneRepository = repository;
    }

    // All phones
    @GetMapping("/phones")
    @ApiOperation(value = "Get all phones")
    List<Phone> all() {
        return phoneRepository.findAll();
    }

    @GetMapping("/phones/{id}")
    @ApiOperation(value = "Get one phone by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got Phone", response = Phone.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Phone not found"),
            @ApiResponse(code = 500, message = "Server error", response = ErrorResponse.class)})
    Phone one(@PathVariable Long id) {

        return phoneRepository.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException(id));
    }

    @PostMapping("/phones")
    @ApiOperation(value = "Create new phone record")
    Phone newEmployee(@RequestBody @Valid Phone newPhone) {
/*        Pattern pattern = Pattern.compile("^\\+371[ .]?\\d{8}$");;
        Matcher matcher = pattern.matcher(newPhone.getPhone());
        if (!matcher.matches()) {
            throw new CustomValidationException("The phone number should follow pattern +371 XXXXXXXX");
        } ;*/
        log.info("Executed POST /phones with input " + newPhone.toString());
        return phoneRepository.save(newPhone);
    }

    @PutMapping("/phones/{id}")
    @ApiOperation(value = "Change phone record")
    Phone replacePerson(@RequestBody Phone newPhone, @PathVariable Long id) {

        return phoneRepository.findById(id)
                .map(phone -> {
                    phone.setPersonid(newPhone.getPersonid());
                    phone.setPhone(newPhone.getPhone());
                    phone.setType(newPhone.getType());
                    return phoneRepository.save(phone);
                })
                .orElseGet(() -> {
                    newPhone.setId(id);
                    return phoneRepository.save(newPhone);
                });
    }

    @DeleteMapping("/phones/{id}")
    @ApiOperation(value = "Delete one phone record by ID")
    void deletePerson(@PathVariable Long id) {
        phoneRepository.deleteById(id);
    }

}
