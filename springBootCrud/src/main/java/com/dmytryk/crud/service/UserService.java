package com.dmytryk.crud.service;

import com.dmytryk.crud.dto.UserDto;

import java.util.Collection;

public interface UserService {

    Collection<UserDto> getUser();

    UserDto postUser(UserDto userDto);

    void deleteUser(String id);

    UserDto updateUser(UserDto userDto);

    void getUserById(String id);
}
