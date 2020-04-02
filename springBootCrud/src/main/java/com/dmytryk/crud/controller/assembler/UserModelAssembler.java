package com.dmytryk.crud.controller.assembler;

import com.dmytryk.crud.controller.UserController;
import com.dmytryk.crud.controller.model.UserDtoModel;
import com.dmytryk.crud.dto.UserDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserDto, UserDtoModel> {

    public UserModelAssembler() {
        super(UserController.class, UserDtoModel.class);
    }

    @Override
    public UserDtoModel toModel(UserDto userDto) {
        UserDtoModel resource = new UserDtoModel();
        resource.setUserDto(userDto);
        resource.add(linkTo(methodOn(UserController.class).getUserById(userDto.getUserId())).withSelfRel());
        resource.add(linkTo(methodOn(UserController.class).postUser(null, null)).withRel("post_url"));
        return resource;
    }
}
