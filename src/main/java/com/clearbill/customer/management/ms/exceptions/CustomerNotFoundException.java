package com.clearbill.customer.management.ms.exceptions;

public class CustomerNotFoundException extends RuntimeException{


    public CustomerNotFoundException(String message) {
        super(message);
    }
}
