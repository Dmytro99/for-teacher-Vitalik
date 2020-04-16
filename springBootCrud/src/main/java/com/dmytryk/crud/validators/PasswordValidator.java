package com.dmytryk.crud.validators;

import com.dmytryk.crud.dto.UserDto;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return UserDto.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is not entered");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatPassword", "",
        "Repeated password is not entered");

    UserDto userDto = (UserDto) target;

    Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    if (!(pattern.matcher(userDto.getPassword()).matches())) {
      errors.rejectValue("password", "Password is not valid");
    }
  }
}
