package com.dmytryk.crud.service;

import com.dmytryk.crud.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

  UserDto signIn(UserDto userDto);

  UserDto signUp(UserDto userDto);

  void signOut();

  void sendRegistrationSuccessMail(String email);

}
