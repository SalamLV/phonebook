package com.spb.rest.mytask.phonebook.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Getter @Setter
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_generator")
    @SequenceGenerator(name="phone_generator", sequenceName = "phone_seq")
    private Long id;

    @Min(value = 1)
    private Long personid;

    @Pattern(regexp="^\\+371[ .]?\\d{8}$")
    private String phone;

    public static enum PhoneEnum {
        mobile, home, work
    }

    @Enumerated(EnumType.STRING)
    private PhoneEnum type;

    Phone() {};

    public Phone(Long personid, String phone, PhoneEnum type) {
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
