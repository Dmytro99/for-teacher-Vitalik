package com.dmytryk.crud.service;

import com.dmytryk.crud.dto.UserDto;
import java.util.List;

public interface UserService {

  List<UserDto> getUsers();

  UserDto postUser(UserDto userDto);

  void deleteUser(String id);

  UserDto updateUser(UserDto userDto);

  UserDto getUserById(String id);
}
