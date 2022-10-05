package dev.decagon.exception;

public class ProductNotInCart extends RuntimeException {
    public ProductNotInCart(String message) {
        super(message);
    }
}
