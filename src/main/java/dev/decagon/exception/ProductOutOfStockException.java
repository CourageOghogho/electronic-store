package dev.decagon.exception;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException(String message){
        super(message);
    }
}
