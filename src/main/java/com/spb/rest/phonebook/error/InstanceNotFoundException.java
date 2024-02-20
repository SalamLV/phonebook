package com.spb.rest.phonebook.error;

public class InstanceNotFoundException extends RuntimeException {
    public InstanceNotFoundException(Long id) {
        super("Could not find data for " + id);
    }
}

