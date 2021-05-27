package com.dmytryk.crud.controller;

import com.dmytryk.crud.api.AuthApi;
import com.dmytryk.crud.controller.assembler.UserModelAssembler;
import com.dmytryk.crud.controller.model.UserModel;
import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.service.AuthService;
import com.dmytryk.crud.validators.EmailValidator;
import com.dmytryk.crud.validators.PasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

  private final AuthService authService;
  private final UserModelAssembler userModelAssembler;
  private final EmailValidator emailValidator;
  private final PasswordValidator passwordValidator;

  @Override
  public void dataBinding(WebDataBinder binder) {
    binder.addValidators(emailValidator, passwordValidator);
  }

  @Override
  public UserModel signIn(UserDto inputUser) {
    log.info("Sign in user with email: {}", inputUser.getEmail());
    UserDto outputUser = authService.signIn(inputUser);
    return userModelAssembler.toResource(outputUser);
  }

  @Override
  public UserModel signUp(UserDto inputUser) {
    log.info("Register user with email: {}", inputUser.getEmail());
    UserDto outputUser = authService.signUp(inputUser);
    return userModelAssembler.toResource(outputUser);
  }

  @Override
  public ResponseEntity<Void> signOut() {
    authService.signOut();
    return ResponseEntity.noContent().build();
  }
}
