package com.simplejava.customer.exception;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 2/25/2023
 * Time: 12:52 AM
 */

public class CustomerNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -1401629323189160267L;

    private final String errorMessage;

    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

}
