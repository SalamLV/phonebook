package com.spb.rest.phonebook.dto;

import com.spb.rest.phonebook.util.constants;
import jakarta.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public record PhoneDTO (
    Long id,

    @Min(value = 1)
    Long personid,

    @Pattern(regexp = "^\\+371[ .]?\\d{8}$")
    String phone,

    @Enumerated(EnumType.STRING)
    constants.PhoneEnum type

) {}
