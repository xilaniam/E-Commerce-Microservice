package com.ecommerce.inventoryservice.exception;

public class ProductDoesntExistsException extends RuntimeException{
    public ProductDoesntExistsException(String msg){
        super(msg);
    }
}
