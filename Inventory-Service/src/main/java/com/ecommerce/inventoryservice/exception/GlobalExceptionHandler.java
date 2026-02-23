package com.ecommerce.inventoryservice.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String > errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(),err.getDefaultMessage()));
        return errors;
    }
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public Map<String,String> handleProductAlreadyExistsException(ProductAlreadyExistsException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("message : " , ex.getMessage());
        return errors;
    }

    @ExceptionHandler(StockNegativeException.class)
    public Map<String,String> handleStockNegativeException(StockNegativeException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("message : " , ex.getMessage());
        return errors;
    }
}
