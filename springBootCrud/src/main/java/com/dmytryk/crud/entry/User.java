package com.dmytryk.crud.entry;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  private String userId;
  private String password;
  private int age;
  @Indexed(unique = true)
  private String email;
  private Gender gender;

}