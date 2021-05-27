package com.dmytryk.crud.controller.assembler;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.dmytryk.crud.controller.UserController;
import com.dmytryk.crud.controller.model.UserModel;
import com.dmytryk.crud.dto.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler extends ResourceAssemblerSupport<UserDto, UserModel> {

  public UserModelAssembler() {
    super(UserController.class, UserModel.class);
  }

  @Override
  public UserModel toResource(UserDto userDto) {
    UserModel resource = new UserModel();
    resource.setUserDto(userDto);
    resource.add(linkTo(methodOn(UserController.class)
        .getUserById(userDto.getUserId())).withSelfRel());
    resource.add(linkTo(methodOn(UserController.class)
        .postUser(null)).withRel("post_url"));

    return resource;
  }

  public Resources<UserModel> toResources(List<UserDto> userDto) {
    List<UserModel> userModels = userDto.stream().map(this::toResource)
        .collect(Collectors.toList());
    return new Resources<>(userModels);
  }
}
