package com.spb.rest.mytask.phonebook.dao;

import com.spb.rest.mytask.phonebook.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
