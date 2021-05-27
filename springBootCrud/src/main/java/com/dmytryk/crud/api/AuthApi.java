package com.dmytryk.crud.api;

import com.dmytryk.crud.controller.model.UserModel;
import com.dmytryk.crud.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "Auth management REST API")
@RequestMapping("/api/v1/auth")
public interface AuthApi {

  @InitBinder
  void dataBinding(WebDataBinder binder);

  @ApiOperation(value = "Sign in user")
  @ApiResponse(code = 200, message = "Ok")
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/signin")
  UserModel signIn(@RequestBody @Validated UserDto userDto);

  @ApiOperation(value = "Sign up user")
  @ApiResponse(code = 201, message = "Created")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/signup")
  UserModel signUp(@RequestBody @Validated UserDto userDto);

  @ApiOperation(value = "Sign out current user")
  @ApiResponse(code = 204, message = "No content")
  @GetMapping("/signout")
  ResponseEntity<Void> signOut();
}
