package com.simplejava.customer.exception;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 2/25/2023
 * Time: 12:52 AM
 */
public class ApplicationException extends RuntimeException {
    private final String errorMessage;

    public ApplicationException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;

    }
}
