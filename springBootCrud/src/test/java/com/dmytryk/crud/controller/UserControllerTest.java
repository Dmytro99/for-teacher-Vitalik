package com.dmytryk.crud.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dmytryk.crud.controller.assembler.UserModelAssembler;
import com.dmytryk.crud.controller.model.UserDtoModel;
import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.entry.Gender;
import com.dmytryk.crud.entry.User;
import com.dmytryk.crud.service.UserService;
import com.dmytryk.crud.validators.EmailValidator;
import com.dmytryk.crud.validators.PasswordValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

  @MockBean
  private UserService userService;
  @MockBean
  private UserModelAssembler userModelAssembler;
  @SpyBean
  private EmailValidator emailValidator;
  @SpyBean
  private PasswordValidator passwordValidator;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  private UserDtoModel userDtoModel;
  private List<UserDto> userList;
  private User user;
  private UserDto userDto;
  private UserDto updatedDto;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    userList = new ArrayList<>();
    userList.add(
        UserDto.builder()
            .userId("32")
            .password("DtRrhc7rmvkgdcs")
            .repeatPassword("DtRrhc7rmvkgdcs")
            .email("yigit@ygt.com")
            .age(18)
            .gender("MALE")
            .build()
    );

    user = User.builder()
        .userId("33")
        .password("DtRrhc7rmvkgdcs")
        .email("userg@ygt.com")
        .age(18)
        .gender(Gender.MALE)
        .build();

    userDto = UserDto.builder()
        .userId("34")
        .password("DtRrhc7rmvkgdcs")
        .repeatPassword("DtRrhc7rmvkgdcs")
        .email("userDto@gygt.com")
        .age(18)
        .gender("FEMALE")
        .build();

    updatedDto = UserDto.builder()
        .userId("34")
        .password("DtRrhc7rmvkgdcs")
        .repeatPassword("DtRrhc7rmvkgdcs")
        .email("m@gmail.com")
        .age(20)
        .gender("MALE")
        .build();

    userDtoModel = new UserDtoModel();
    userDtoModel.setUserDto(userDto);

  }

  @Test
  public void getUsers() throws Exception {
    when(userService.getUsers()).thenReturn(userList);
    when(userModelAssembler.toResources(anyList())).thenReturn(new Resources<>(
        Collections.singletonList(userDtoModel)));

    mockMvc.perform(get("/user")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(jsonPath("$._embedded.userDtoModelList", hasSize(1)))
        .andExpect(jsonPath("$._embedded.userDtoModelList[0].email").value("userDto@gygt.com"));
  }

  @Test
  public void getUserById() throws Exception {
    when(userService.getUserById(user.getUserId())).thenReturn(userDto);
    when(userModelAssembler.toResource(userDto)).thenReturn(userDtoModel);

    mockMvc.perform(get("/user/33")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(jsonPath("$.gender", is("FEMALE")));
  }

  @Test
  public void postUser() throws Exception {
    when(userService.postUser(any(UserDto.class))).thenReturn(userDto);
    when(userModelAssembler.toResource(userDto)).thenReturn(userDtoModel);

    String jsonString = new ObjectMapper().writeValueAsString(userDto);

    mockMvc.perform(post("/user")
        .content(jsonString)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.email").value("userDto@gygt.com"));
  }

  @Test
  public void deleteUser() throws Exception {
    given(userService.getUserById(user.getUserId())).willReturn(userDto);
    doNothing().when(userService).deleteUser(any(String.class));

    mockMvc.perform(delete("/user/33")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNoContent());

    verify(userService, times(1))
        .deleteUser(user.getUserId());
  }


  @Test
  public void updateUser() throws Exception {
    when(userService.updateUser(any(UserDto.class))).thenReturn(updatedDto);

    String jsonString = new ObjectMapper().writeValueAsString(updatedDto);

    mockMvc.perform(put("/user/{id}", 34)
        .content(jsonString)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.age").value(20));
  }
}