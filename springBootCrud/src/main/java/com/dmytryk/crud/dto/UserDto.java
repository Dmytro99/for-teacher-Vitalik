package com.dmytryk.crud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserDto {
    @ApiModelProperty(notes = "Identification number of user")
    private String userId;
    @ApiModelProperty(notes = "User password")
    private String password;
    @ApiModelProperty(notes = "User repeated password")
    private String repeatPassword;
    @ApiModelProperty(notes = "User contact email")
    @Email(message = "Enter a valid email address.")
    private String email;
    @ApiModelProperty(notes = "User age")
    private int age;
    @ApiModelProperty(notes = "User gender")
    private String gender;

}
