package com.dmytryk.crud.service.impl;

import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.entry.User;
import com.dmytryk.crud.exception.UserNotFoundException;
import com.dmytryk.crud.mapper.UserMapper;
import com.dmytryk.crud.repository.UserRepository;
import com.dmytryk.crud.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
  private final UserRepository repository;

  public List<UserDto> getUsers() {
    List<User> userList = repository.findAll();
    if (userList.isEmpty()) {
      log.warn("getUser: no user is found");
      throw new UserNotFoundException("There are no user found");
    } else {
      return INSTANCE.listToUserDto(userList);
    }
  }

  @Override
  public UserDto getUserById(String id) {
    Optional<User> user = repository.findById(id);
    user.orElseThrow(() -> {
      log.warn("getUserById: no user is found");
      return new UserNotFoundException("Requested user is not found");
    });
    return INSTANCE.userToUserDto(user.get());
  }

  @Override
  public UserDto postUser(UserDto userDto) {
    User user = INSTANCE.userDtoToUser(userDto);
    return INSTANCE.userToUserDto(repository.insert(user));
  }

  @Override
  public void deleteUser(String id) {
    repository.deleteById(id);
    log.info("User: " + id + "successfully deleted");
  }

  @Override
  public UserDto updateUser(UserDto userDto) {
    User user = INSTANCE.userDtoToUser(userDto);
    return INSTANCE.userToUserDto(repository.save(user));
  }
}
