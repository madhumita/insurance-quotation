package com.quotation.exception;

import org.springframework.stereotype.Component;


public class OccupationNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public OccupationNotFoundException(String message) {
        super(message);
    }
}