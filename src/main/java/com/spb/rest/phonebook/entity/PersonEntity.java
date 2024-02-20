package com.spb.rest.phonebook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter @Setter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name="person_generator", sequenceName = "person_seq")
    private Long id;

    private String first_name;

    private String last_name;

    private Date birthday;

    public PersonEntity() {};

    public PersonEntity(String first_name, String last_name, Date birthday) throws Exception {
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

