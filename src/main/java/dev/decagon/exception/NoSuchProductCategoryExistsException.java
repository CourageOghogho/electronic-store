package dev.decagon.exception;

public class NoSuchProductCategoryExistsException extends RuntimeException {
    public NoSuchProductCategoryExistsException(String message){
        super(message);
    }
}
