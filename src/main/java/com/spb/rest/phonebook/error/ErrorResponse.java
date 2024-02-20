package com.spb.rest.phonebook.error;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private String code;
    private List<String> messages;

    public ErrorResponse(String code, List<String> messages) {
        this.code = code;
        this.messages = messages;
    }
}