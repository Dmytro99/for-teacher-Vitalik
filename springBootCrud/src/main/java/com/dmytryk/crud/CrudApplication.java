package com.dmytryk.crud;

import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudApplication.class, args);
  }

  @Bean
  public CommandLineRunner basicAuthUser(AuthService authService,
      @Value("${app.auth.user.password}") String password,
      @Value("${app.auth.user.email}") String email) {
    return args -> {
      UserDto userDto = new UserDto();
      userDto.setEmail(email);
      userDto.setPassword(password);
      userDto.setRepeatPassword(password);
      authService.signIn(userDto);
    };
  }
}
