package com.dmytryk.crud.controller;

import com.dmytryk.crud.exception.UserGenderNotMatchEnum;
import com.dmytryk.crud.exception.UserNotFoundException;
import com.dmytryk.crud.exception.UserPasswordNotMatchException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Data
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserPasswordNotMatchException.class)
    public ResponseEntity<String> handle(UserPasswordNotMatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handle(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(UserGenderNotMatchEnum.class)
    public ResponseEntity<String> handle(UserGenderNotMatchEnum exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }

}
