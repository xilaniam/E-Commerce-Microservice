package com.ecommerce.authservice.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String msg){
        super(msg);
    }
}
