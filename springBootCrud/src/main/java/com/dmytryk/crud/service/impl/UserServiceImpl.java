package com.dmytryk.crud.service.impl;

import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.entry.User;
import com.dmytryk.crud.mapper.UserMapper;
import com.dmytryk.crud.repository.UserRepository;
import com.dmytryk.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public Collection<UserDto> getUser() {
        return UserMapper.INSTANCE.listToUserDto(repository.findAll());
    }

    @Override
    public void getUserById(String id) {
        repository.findById(id);
    }

    @Override
    public UserDto postUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        return UserMapper.INSTANCE.userToUserDto(repository.insert(user));
    }

    @Override
    public void deleteUser(String id) {
        repository.deleteById(id);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        return UserMapper.INSTANCE.userToUserDto(repository.save(user));
    }
}
