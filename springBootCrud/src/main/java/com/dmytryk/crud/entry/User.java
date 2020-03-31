package com.dmytryk.crud.entry;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
//TODO: robo 3T

//TODO: add Swagger2, rest, rest 6 restrictions, rest vs soap, rest maturity model, hateoas

@Data
@Document
@RequiredArgsConstructor
public class User {
    @Id
    private String userId;
    private String password;
    private int age;
    @Indexed(unique = true)
    private String email;

    private Gender gender;

}