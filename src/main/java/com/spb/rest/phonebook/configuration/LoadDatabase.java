package com.spb.rest.phonebook.configuration;

import com.spb.rest.phonebook.entity.PersonEntity;
import com.spb.rest.phonebook.entity.PhoneEntity;
import com.spb.rest.phonebook.repository.PersonRepository;
import com.spb.rest.phonebook.repository.PhoneRepository;
import com.spb.rest.phonebook.util.constants;
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
            log.info("Preloading " + personRepository.save(new PersonEntity("Barak", "Obama", new SimpleDateFormat("dd-MM-yyyy").parse("01-05-1980") )));
            log.info("Preloading " + personRepository.save(new PersonEntity("Donald", "Trump", new SimpleDateFormat("dd-MM-yyyy").parse("01-10-1985"))));
        };
    }

    @Bean
    CommandLineRunner initDatabase2(PhoneRepository phoneRepository) {

        return args -> {
            log.info("Preloading " + phoneRepository.save(new PhoneEntity( (long)1, "+371 26758999",  constants.PhoneEnum.mobile)));
            log.info("Preloading " + phoneRepository.save(new PhoneEntity( (long)2, "+371 26758777", constants.PhoneEnum.home)));
        };
    }
}