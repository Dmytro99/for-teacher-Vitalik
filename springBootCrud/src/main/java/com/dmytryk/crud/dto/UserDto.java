package com.dmytryk.crud.dto;

import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String password;
    private String repeatPassword;
    private String email;
    private int age;
    private String gender;

}
