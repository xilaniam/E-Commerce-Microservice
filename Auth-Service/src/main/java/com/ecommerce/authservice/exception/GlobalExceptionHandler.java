package com.ecommerce.authservice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Map<String,String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("message : ",ex.getMessage());
        return errors;
    }
}
