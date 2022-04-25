package com.spb.rest.mytask.phonebook.dao;

import com.spb.rest.mytask.phonebook.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
