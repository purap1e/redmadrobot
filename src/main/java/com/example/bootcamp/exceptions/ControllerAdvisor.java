package com.example.bootcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Object> handleUsernameExistsException(EmailExistsException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorCode", "email_exists");
        body.put("errorDescription", String.format("The email '%s' already exists", ex.getEmail()));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
