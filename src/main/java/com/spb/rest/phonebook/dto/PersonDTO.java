package com.spb.rest.phonebook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public record PersonDTO (
    @ApiModelProperty(position = 0)
    Long id,

    @NotBlank (message = "First name is mandatory")
    @ApiModelProperty(notes = "First name", example = "Graham", position = 1)
    @Size(max = 50)
    String first_name,

    @NotBlank (message = "Last name is mandatory")
    @ApiModelProperty(notes = "Last name", example = "Smith", position = 2)
    @Size(max = 100)
    String last_name,

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(position = 3)
    @Past
    Date birthday
) {}

