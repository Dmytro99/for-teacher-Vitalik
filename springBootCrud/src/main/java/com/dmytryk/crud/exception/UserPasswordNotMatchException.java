package com.dmytryk.crud.exception;

public class UserPasswordNotMatchException extends AbstractRuntimeException {

    public UserPasswordNotMatchException(String message) {
        super(message);
    }
}
