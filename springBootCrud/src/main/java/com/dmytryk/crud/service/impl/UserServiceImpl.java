package com.dmytryk.crud.service.impl;

import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.entry.User;
import com.dmytryk.crud.exception.UserNotFoundException;
import com.dmytryk.crud.mapper.UserMapper;
import com.dmytryk.crud.repository.UserRepository;
import com.dmytryk.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public Collection<UserDto> getUser() {
        List<User> userList = repository.findAll();
        if (userList.isEmpty()) {
            throw new UserNotFoundException("There are no user found");
        } else {
            return UserMapper.INSTANCE.listToUserDto(repository.findAll());

        }

    }

    @Override
    public UserDto getUserById(String id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return UserMapper.INSTANCE.userToUserDto(user.get());
        }
        throw new UserNotFoundException("Requested user is not found");
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
