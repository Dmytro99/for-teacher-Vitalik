package com.dmytryk.crud.api;

import com.dmytryk.crud.controller.model.UserModel;
import com.dmytryk.crud.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "User management REST API")
@RequestMapping("/api/v1/users")
public interface UserApi {


  @InitBinder
  void dataBinding(WebDataBinder binder);

  @ApiOperation(value = "Returns list of all users")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  Resources<UserModel> getUsers();

  @ApiOperation(value = "Returns a specific user by user Id")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{id}")
  UserModel getUserById(@PathVariable("id") String id);

  @ApiOperation(value = "Add new user to the list of users")
  @PostMapping
  ResponseEntity<UserModel> postUser(@RequestBody @Valid UserDto userDto);

  @ApiOperation(value = "Delete a user from the list by user Id")
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  ResponseEntity<?> deleteUser(@PathVariable("id") String id);

  @ApiOperation(value = "Update specific user from the list of all users")
  @PutMapping(value = "/{id}")
  ResponseEntity<UserDto> updateUser(@PathVariable("id") String id,
      @RequestBody @Valid UserDto userDto);
}
