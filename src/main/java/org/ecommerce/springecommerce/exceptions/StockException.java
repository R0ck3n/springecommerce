package org.ecommerce.springecommerce.exceptions;

public class StockException extends RuntimeException {
    public StockException(String message) {
        super(message);
    }
}
