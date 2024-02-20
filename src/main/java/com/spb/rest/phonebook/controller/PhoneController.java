package com.spb.rest.phonebook.controller;

import com.spb.rest.phonebook.dto.PhoneDTO;
import com.spb.rest.phonebook.entity.PhoneEntity;
import com.spb.rest.phonebook.repository.PhoneRepository;
import com.spb.rest.phonebook.error.ErrorResponse;
import com.spb.rest.phonebook.error.InstanceNotFoundException;
import com.spb.rest.phonebook.service.PhoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Phone")
public class PhoneController {

    private final PhoneRepository phoneRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private PhoneService phoneService;

   public PhoneController(PhoneRepository repository, PhoneService phoneService) {
        this.phoneRepository = repository;
        this.phoneService = phoneService;
    }

    // All phones
    @GetMapping("/phones")
    @ApiOperation(value = "Get all phones")
    List<PhoneDTO> all() {
        return this.phoneService.getAllPhones();
    }

    @GetMapping("/phones/{id}")
    @ApiOperation(value = "Get one phone by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got Phone", response = PhoneEntity.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Phone not found"),
            @ApiResponse(code = 500, message = "Server error", response = ErrorResponse.class)})
    PhoneDTO one(@PathVariable Long id) {
       return this.phoneService.getPhone(id);
    }

    @PostMapping("/phones")
    @ApiOperation(value = "Create new phone record")
    PhoneDTO newPhone(@RequestBody @Valid PhoneDTO newPhoneDTO) {
/*        Pattern pattern = Pattern.compile("^\\+371[ .]?\\d{8}$");;
        Matcher matcher = pattern.matcher(newPhone.getPhone());
        if (!matcher.matches()) {
            throw new CustomValidationException("The phone number should follow pattern +371 XXXXXXXX");
        } ;*/
        log.info("Executed POST /phones with input " + newPhoneDTO.toString());
        return this.phoneService.savePhone(newPhoneDTO);
    }

    @PutMapping("/phones/{id}")
    @ApiOperation(value = "Change phone record")
    PhoneDTO updatePhone(@RequestBody PhoneDTO newPhoneDTO, @PathVariable Long id) {
       return this.phoneService.updatePhone(newPhoneDTO, id);
    }

    @DeleteMapping("/phones/{id}")
    @ApiOperation(value = "Delete one phone record by ID")
    void deletePhone(@PathVariable Long id) {
        this.phoneService.deletePhone(id);
    }

}
