package com.ca.sample.exception;

public class VendingOutOfCapacityException extends RuntimeException {

    private static final long serialVersionUID = -2291432992701521212L;

    public VendingOutOfCapacityException() {
    }

    public VendingOutOfCapacityException(String message) {
        super(message);
    }
}
