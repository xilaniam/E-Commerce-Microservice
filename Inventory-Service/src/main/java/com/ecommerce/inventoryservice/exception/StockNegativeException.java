package com.ecommerce.inventoryservice.exception;

public class StockNegativeException extends RuntimeException{
    public StockNegativeException(String msg){
        super(msg);
    }
}
