package dev.decagon.exception;

public class RoleAccessDeniedException extends RuntimeException {
    public RoleAccessDeniedException(String message){
        super(message);
    }
}
