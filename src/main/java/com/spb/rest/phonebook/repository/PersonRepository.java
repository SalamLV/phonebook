package com.spb.rest.phonebook.repository;

import com.spb.rest.phonebook.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
