package com.spb.rest.mytask.phonebook.configuration;

import com.spb.rest.mytask.phonebook.dao.PersonRepository;
import com.spb.rest.mytask.phonebook.dao.PhoneRepository;
import com.spb.rest.mytask.phonebook.model.Person;
import com.spb.rest.mytask.phonebook.model.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase1(PersonRepository personRepository) {

        return args -> {
            log.info("Preloading " + personRepository.save(new Person("Barak", "Obama", new SimpleDateFormat("dd-MM-yyyy").parse("01-05-1980") )));
            log.info("Preloading " + personRepository.save(new Person("Donald", "Trump", new SimpleDateFormat("dd-MM-yyyy").parse("01-10-1985"))));
        };
    }

    @Bean
    CommandLineRunner initDatabase2(PhoneRepository phoneRepository) {

        return args -> {
            log.info("Preloading " + phoneRepository.save(new Phone( (long)1, "+371 26758999",  Phone.PhoneEnum.mobile)));
            log.info("Preloading " + phoneRepository.save(new Phone( (long)2, "+371 26758777", Phone.PhoneEnum.home)));
        };
    }
}