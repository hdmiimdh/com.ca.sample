package com.ca.sample.exception;

public class VendingNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6376635630897090122L;

    public VendingNotFoundException() {
    }

    public VendingNotFoundException(String message) {
        super(message);
    }
}
