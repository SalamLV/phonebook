package com.spb.rest.mytask.phonebook.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlers {
        private final Logger log = LoggerFactory.getLogger(ExceptionHandlers.class);

        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        @ExceptionHandler(Throwable.class)
        @ResponseBody
        public ErrorResponse handleThrowable(final Throwable ex) {
                log.error("Unexpected error", ex);
                List<String> errorList = new ArrayList<>();
                errorList.add("An unexpected internal server error occurred");
                return new ErrorResponse("INTERNAL_SERVER_ERROR", errorList);
        }

        @ResponseBody
        @ExceptionHandler(InstanceNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ErrorResponse instanceNotFoundHandler(InstanceNotFoundException ex) {
                log.error("Instance not found error", ex);
                List<String> errorList = new ArrayList<>();
                errorList.add(ex.getMessage());
            return new ErrorResponse(HttpStatus.NOT_FOUND.toString(), errorList);
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseBody
        public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
                List<String> errorList = new ArrayList<>();
                for(FieldError error : ex.getBindingResult().getFieldErrors()) {
                        String errorMessage = "Field error in object '" + error.getObjectName() + "' on field '" + error.getField() +
                                "': rejected value [" + error.getRejectedValue() + "]; " + error.getDefaultMessage();
                        errorList.add(errorMessage);
                }
                log.error("Validation error", ex);
                return new ErrorResponse("BAD_REQUEST", errorList);
        }

        // Validation error
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(CustomValidationException.class)
        @ResponseBody
        public ErrorResponse handleCustomValidationException(CustomValidationException ex) {
                log.error("Custom validation Error", ex);
                List<String> errorList = new ArrayList<>();
                errorList.add(ex.getMessage());

                return new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), errorList);
        }
}
