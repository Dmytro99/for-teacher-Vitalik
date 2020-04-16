package com.dmytryk.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.entry.Gender;
import com.dmytryk.crud.entry.User;
import com.dmytryk.crud.exception.UserNotFoundException;
import com.dmytryk.crud.repository.UserRepository;
import com.dmytryk.crud.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;
  @InjectMocks
  private UserServiceImpl userService;

  private List<User> userList;
  private User user;
  private UserDto userDto;

  @Before
  public void setUp() {
    userList = new ArrayList<>();
    userList.add(
        User.builder()
            .userId("32")
            .password("DtRrhc7rmvkgdcs")
            .email("yigit@ygt.com")
            .age(18)
            .gender(Gender.MALE)
            .build()
    );
    user = User.builder()
        .userId("33")
        .password("DtRrhc7rmvkgdcs")
        .email("yig@ygt.com")
        .age(18)
        .gender(Gender.FEMALE)
        .build();

    userDto = UserDto.builder()
        .userId("34")
        .password("DtRrhc7rmvkfgdcs")
        .repeatPassword("DtRrhc7rmvkfgdcs")
        .email("qwerty@ygt.com")
        .age(58)
        .gender("MALE")
        .build();
  }

  @Test
  public void getUsersTest() {
    when(userRepository.findAll()).thenReturn(userList);
    List<UserDto> users = userService.getUsers();
    assertEquals(userList.get(0).getUserId(), users.get(0).getUserId());
  }

  @Test(expected = UserNotFoundException.class)
  public void getUserNotFoundExceptionTest() {
    when(userRepository.findAll()).thenReturn(Collections.emptyList());
    userService.getUsers();
  }

  @Test(expected = UserNotFoundException.class)
  public void getUserByIdNotFoundExceptionTest() {
    user = new User();
    when(userRepository.findById("2")).thenThrow(new UserNotFoundException("User not found"));
    userService.getUserById("2");
  }

  @Test()
  public void getUserByIdTest() {
    when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(user));
    UserDto userDto = userService.getUserById(user.getUserId());
    assertNotNull(userDto);
    assertEquals(user.getUserId(), userDto.getUserId());

  }

  @Test
  public void deleteUserTest() {
    userService.deleteUser(user.getUserId());
    verify(userRepository, times(1))
        .deleteById(user.getUserId());
  }

  @Test
  public void postUser() {
    when(userRepository.insert(any(User.class))).thenReturn(user);
    UserDto returnedUser = userService.postUser(userDto);
    assertEquals(returnedUser.getAge(), user.getAge());

  }

  @Test
  public void updateUser() {
    when(userRepository.save(any(User.class))).thenReturn(user);
    UserDto updated = userService.updateUser(userDto);
    assertNotNull(updated);
    assertEquals(user.getAge(), updated.getAge());
    assertEquals(user.getEmail(), updated.getEmail());
  }
}