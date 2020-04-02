package com.dmytryk.crud.mapper;

import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.entry.Gender;
import com.dmytryk.crud.entry.User;
import com.dmytryk.crud.exception.UserGenderNotMatchEnum;
import com.dmytryk.crud.exception.UserPasswordNotMatchException;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mapper
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @BeforeMapping
    void checkPassword(UserDto userDto) {
        if (!StringUtils.equals(userDto.getPassword(), userDto.getRepeatPassword())) {
            throw new UserPasswordNotMatchException("Please make sure your passwords match.");
        }
    }

    @Mapping(target = "gender", source = "userDto.gender", qualifiedByName = "getGenderForUserDto")
    public abstract User userDtoToUser(UserDto userDto);


    @Mapping(target = "repeatPassword", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "gender", source = "user.gender", qualifiedByName = "getGenderForUser")
    public abstract UserDto userToUserDto(User user);

    @Named("getGenderForUserDto")
    public Gender getGenderForUserDto(UserDto userDto) {
        for (Gender genderVal : Gender.values()) {
            if (genderVal.name().equalsIgnoreCase(userDto.getGender())) {
                return genderVal;
            }
        }
        throw new UserGenderNotMatchEnum("There are no matching of Enum GENDER.");
    }

    @Named("getGenderForUser")
    public String getGenderForUser(UserDto userDto) {
        String female = Gender.FEMALE.name();
        return userDto.getGender().equals(female) ? female : Gender.MALE.name();
    }

    public abstract List<UserDto> listToUserDto(Collection<User> users);
}

