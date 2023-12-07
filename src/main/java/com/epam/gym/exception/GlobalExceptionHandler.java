package com.epam.gym.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(String.format(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceCreationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleEntityCreationException(ResourceCreationException e) {
        return new ResponseEntity<>(String.format(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
