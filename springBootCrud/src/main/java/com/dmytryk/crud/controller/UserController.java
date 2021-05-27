package com.dmytryk.crud.controller;

import com.dmytryk.crud.api.UserApi;
import com.dmytryk.crud.controller.assembler.UserModelAssembler;
import com.dmytryk.crud.controller.model.UserModel;
import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.service.UserService;
import com.dmytryk.crud.validators.EmailValidator;
import com.dmytryk.crud.validators.PasswordValidator;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;
  private final EmailValidator emailValidator;
  private final PasswordValidator passwordValidator;
  private final UserModelAssembler userModelAssembler;

  @Override
  public void dataBinding(WebDataBinder binder) {
    binder.addValidators(emailValidator, passwordValidator);
  }

  @Override
  public Resources<UserModel> getUsers() {
    List<UserDto> userDtoList = userService.getUsers();
    return userModelAssembler.toResources(userDtoList);
  }

  @Override
  public UserModel getUserById(@PathVariable("id") String id) {
    UserDto getById = userService.getUserById(id);
    return userModelAssembler.toResource(getById);
  }

  @Override
  public ResponseEntity<UserModel> postUser(@RequestBody @Valid UserDto userDto) {
    UserDto post = userService.postUser(userDto);
    UserModel userModel = userModelAssembler.toResource(post);
    return new ResponseEntity<>(userModel, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
    userService.deleteUser(id);
    return new ResponseEntity<>("User is successfully deleted", HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<UserDto> updateUser(@PathVariable("id") String id,
      @RequestBody @Valid UserDto userDto) {
    UserDto updateUser = userService.updateUser(userDto);
    return new ResponseEntity<>(updateUser, HttpStatus.OK);
  }
}
