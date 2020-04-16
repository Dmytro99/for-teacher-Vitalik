package com.dmytryk.crud.exception;

public class UserNotFoundException extends AbstractRuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
