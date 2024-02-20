package com.spb.rest.phonebook.entity;

import com.spb.rest.phonebook.util.constants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Getter @Setter
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_generator")
    @SequenceGenerator(name="phone_generator", sequenceName = "phone_seq")
    private Long id;

    private Long personid;

    private String phone;

    private constants.PhoneEnum type;

    public PhoneEntity() {};

    public PhoneEntity(Long personid, String phone, constants.PhoneEnum type) {
        this.personid = personid;
        this.phone = phone;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", personid=" + personid +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                '}';
    }
}
