package com.ca.sample.domain;


import com.ca.sample.exception.IllegalVendingTypeException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum VendingType {
    SODA,
    CANDY,
    TOY;

    @JsonCreator
    public static VendingType fromString(final String value) {

        if (value == null) {
            return null;
        }
        for (VendingType vendingType : VendingType.values()) {
            if (vendingType.name().equalsIgnoreCase(value)) {
                return vendingType;
            }
        }
        throw new IllegalVendingTypeException();
    }
}
