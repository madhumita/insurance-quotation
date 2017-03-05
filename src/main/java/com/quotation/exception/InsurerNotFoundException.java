package com.quotation.exception;

import org.springframework.stereotype.Component;

/**Custom Exception to cover scenarios where Insurer is not found
 * @author MADHUMITA
 *
 */
public class InsurerNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public InsurerNotFoundException(String message) {
        super(message);
    }
}