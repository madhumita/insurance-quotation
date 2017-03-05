package com.quotation.exception;

import org.springframework.stereotype.Component;


public class PostCodeNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public PostCodeNotFoundException(String message) {
        super(message);
    }
}