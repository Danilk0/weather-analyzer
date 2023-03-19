package com.moskalyuk.weatheranalyzer.http.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice(basePackages = "com.moskalyuk.weatheranalyzer.http.controller")
public class RestControllerExceptionHandler{

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorMessage> dataTimeParseException(DateTimeParseException exception) {
        return ResponseEntity
                .status(HttpStatus.valueOf(400))
                .body(new ErrorMessage(exception.getMessage()));
    }


}
