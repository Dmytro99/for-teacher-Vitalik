package com.dmytryk.crud.exception;

public class UserPasswordNotMatchException extends RuntimeException {

    public UserPasswordNotMatchException(String message) {
        super(message);
    }
}
