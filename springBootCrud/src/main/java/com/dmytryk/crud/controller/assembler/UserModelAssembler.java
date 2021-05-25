package com.dmytryk.crud.controller.assembler;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.dmytryk.crud.controller.UserController;
import com.dmytryk.crud.controller.model.UserDtoModel;
import com.dmytryk.crud.dto.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler extends ResourceAssemblerSupport<UserDto, UserDtoModel> {

  public UserModelAssembler() {
    super(UserController.class, UserDtoModel.class);
  }

  @Override
  public UserDtoModel toResource(UserDto userDto) {
    UserDtoModel resource = new UserDtoModel();
    resource.setUserDto(userDto);
    resource.add(linkTo(methodOn(UserController.class)
        .getUserById(userDto.getUserId())).withSelfRel());
    resource.add(linkTo(methodOn(UserController.class)
        .postUser(null)).withRel("post_url"));

    return resource;
  }

  public Resources<UserDtoModel> toResources(List<UserDto> userDto) {
    List<UserDtoModel> userDtoModels = userDto.stream().map(this::toResource)
        .collect(Collectors.toList());
    return new Resources<>(userDtoModels);
  }
}
