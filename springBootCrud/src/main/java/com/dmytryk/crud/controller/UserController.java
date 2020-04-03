package com.dmytryk.crud.controller;

import com.dmytryk.crud.controller.assembler.UserModelAssembler;
import com.dmytryk.crud.controller.model.UserDtoModel;
import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.service.impl.UserServiceImpl;
import com.dmytryk.crud.validators.EmailValidator;
import com.dmytryk.crud.validators.PasswordValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(tags = "User Controller")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final UserModelAssembler userModelAssembler;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(emailValidator, passwordValidator);
    }

    @ApiOperation(value = "Returns list of all users")
    @GetMapping
    public CollectionModel<UserDtoModel> getUser() {
        Collection<UserDto> userDtoList = userServiceImpl.getUser();
        return userModelAssembler.toCollectionModel(userDtoList);
    }

    @ApiOperation(value = "Returns a specific user by user Id")
    @GetMapping(value = "/{id}")
    public UserDtoModel getUserById(@PathVariable("id") String id) {
        UserDto getById = userServiceImpl.getUserById(id);
        return userModelAssembler.toModel(getById);
    }

    @ApiOperation(value = "Add new user to the list of users")
    @PostMapping
    public UserDtoModel postUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        UserDto post = userServiceImpl.postUser(userDto);
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Error connected with binding");
//                                                                                TODO: WHAT I NEED TO RETURN????????
        }
        return userModelAssembler.toModel(post);
    }

    @ApiOperation(value = "Delete a user from the list by user Id")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") String id) {
        userServiceImpl.deleteUser(id);
    }

    @ApiOperation(value = "Update specific user from the list of all users")
    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userServiceImpl.updateUser(userDto);
    }
}
