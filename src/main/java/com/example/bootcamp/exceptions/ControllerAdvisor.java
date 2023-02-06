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
    @ExceptionHandler(FileTooLargeException.class)
    public ResponseEntity<Object> handleFileTooLargeException(FileTooLargeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorCode","file is too large");
        body.put("errorDescription", String.format("The file '%s' is too large", ex.getFileName()));

        return new ResponseEntity<>(body, HttpStatus.PAYLOAD_TOO_LARGE);
    }
}
