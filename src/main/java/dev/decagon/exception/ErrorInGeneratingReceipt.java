package dev.decagon.exception;

public class ErrorInGeneratingReceipt extends RuntimeException {
    public ErrorInGeneratingReceipt(String message) {
        super(message);
    }
}
