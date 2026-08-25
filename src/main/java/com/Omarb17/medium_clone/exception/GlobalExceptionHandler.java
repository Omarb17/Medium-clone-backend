package com.Omarb17.medium_clone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleUserNotFoundException (UserNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problemDetail.setTitle("Resourse Not Found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setInstance(
                ServletUriComponentsBuilder
                        .fromCurrentRequestUri()
                        .build()
                        .toUri()
        );
        problemDetail.setType(URI.create("/errors/user-not-found"));

        return problemDetail;
    }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ProblemDetail handleValidationErrors (MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setTitle("Validation Error");
        problemDetail.setDetail("One or more fields are invalid.");

         Map<String, List<String>> errors = new HashMap<>();

         e.getBindingResult()
                 .getFieldErrors()
                 .forEach(error ->
                         errors.computeIfAbsent(
                                 error.getField(),
                                 key -> new ArrayList<>()
                         ).add(error.getDefaultMessage())
                 );

         problemDetail.setProperty("errors", errors);


         problemDetail.setInstance(
                ServletUriComponentsBuilder
                        .fromCurrentRequestUri()
                        .build()
                        .toUri()
        );
        problemDetail.setType(URI.create("/errors/validation-error"));

        return problemDetail;
     }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception e){
        return ResponseEntity.status(500).body("Server Error");
    }
}
