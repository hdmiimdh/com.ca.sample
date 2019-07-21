package com.ca.sample.exception;

public class IllegalVendingTypeException extends RuntimeException {

    private static final long serialVersionUID = 7488838276964976529L;

    public IllegalVendingTypeException() {
    }

    public IllegalVendingTypeException(String message) {
        super(message);
    }
}
