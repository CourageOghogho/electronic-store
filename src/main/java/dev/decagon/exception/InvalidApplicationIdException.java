package dev.decagon.exception;

public class InvalidApplicationIdException extends RuntimeException {
    public InvalidApplicationIdException(String message) {
        super(message);
    }
}
