package com.dmytryk.crud.controller;

import com.dmytryk.crud.exception.UserGenderNotMatchEnumException;
import com.dmytryk.crud.exception.UserNotFoundException;
import com.dmytryk.crud.exception.UserPasswordNotMatchException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Data
@RestControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler({UserPasswordNotMatchException.class, UserNotFoundException.class,
      UserGenderNotMatchEnumException.class})
  public ResponseEntity<String> handle(RuntimeException runtimeException) {
    log.warn("Custom runtime exception was thrown.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(runtimeException.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handle(Exception exception) {
    log.warn("Unexpected exception occurred.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }
}
