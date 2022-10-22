package dev.decagon.exception;

public class NoOrderException extends RuntimeException {
    public NoOrderException(String order_cannot_be_null) {
        super(order_cannot_be_null);
    }
}
