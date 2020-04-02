package com.dmytryk.crud.controller.model;

import com.dmytryk.crud.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class UserDtoModel extends RepresentationModel<UserDtoModel> {

    @JsonUnwrapped
    private UserDto userDto;
}
