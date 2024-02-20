package com.spb.rest.phonebook.repository;

import com.spb.rest.phonebook.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
}
