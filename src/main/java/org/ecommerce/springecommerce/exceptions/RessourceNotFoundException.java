package org.ecommerce.springecommerce.exceptions;

public class RessourceNotFoundException extends RuntimeException {
    public RessourceNotFoundException(String message) {
        super(message);
    }
}
