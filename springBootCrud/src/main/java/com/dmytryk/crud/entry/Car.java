package com.dmytryk.crud.entry;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "Car")
public class Car {

    @Id
    private String carId;
    private String model;
    private String year;
    private int speed;
}