package com.dmytryk.crud.validators;

import com.dmytryk.crud.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmailValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return UserDto.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object target, Errors errors) {
    UserDto userDto = (UserDto) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is not entered");
    if (!userDto.getEmail().contains("@")) {
      errors.rejectValue("email", "Email is not valid.");
    }
  }
}
