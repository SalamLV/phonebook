package com.spb.rest.mytask.phonebook.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spb.rest.mytask.phonebook.error.CustomValidationException;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter @Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name="person_generator", sequenceName = "person_seq")
    @ApiModelProperty(position = 0)
    private Long id;
    @NotBlank (message = "First name is mandatory")
    @ApiModelProperty(notes = "First name", example = "Graham", position = 1)
    @Size(max = 50)
    private String first_name;
    @NotBlank (message = "Last name is mandatory")
    @ApiModelProperty(notes = "Last name", example = "Smith", position = 2)
    @Size(max = 100)
    private String last_name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(position = 3)
    @Past
    private Date birthday;

    Person() {};

    public Person(String first_name, String last_name, Date birthday) throws Exception {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        return "Employee{" + "id=" + this.id + ", name='" + this.first_name + '\'' + ", lastname='" + this.last_name + '\'' +
                ", birthday='" + df.format(this.birthday) +'}';
    }
}

