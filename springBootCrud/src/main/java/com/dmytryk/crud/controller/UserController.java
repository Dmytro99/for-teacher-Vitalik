package com.dmytryk.crud.controller;

import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public Collection<UserDto> getUser() {
        return userServiceImpl.getUser();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserById(@PathVariable("id") String id) {
        userServiceImpl.getUserById(id);
    }

    @PostMapping
    public UserDto postUser(@RequestBody UserDto userDto) {
        return userServiceImpl.postUser(userDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") String id) {
        userServiceImpl.deleteUser(id);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userServiceImpl.updateUser(userDto);
    }
}
