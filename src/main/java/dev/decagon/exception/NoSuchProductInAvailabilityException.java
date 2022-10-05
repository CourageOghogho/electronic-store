package dev.decagon.exception;

public class NoSuchProductInAvailabilityException extends RuntimeException {
    public NoSuchProductInAvailabilityException(String message){
        super(message);
    }
}
