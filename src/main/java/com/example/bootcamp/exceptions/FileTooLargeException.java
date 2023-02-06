package com.example.bootcamp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@ResponseStatus(code = HttpStatus.PAYLOAD_TOO_LARGE, reason = "file is too large")
public class FileTooLargeException extends Exception{
    private String fileName;
}
