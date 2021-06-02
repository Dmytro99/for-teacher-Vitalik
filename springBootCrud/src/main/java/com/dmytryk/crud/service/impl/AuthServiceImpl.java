package com.dmytryk.crud.service.impl;

import com.dmytryk.crud.dto.UserDto;
import com.dmytryk.crud.entry.User;
import com.dmytryk.crud.mapper.UserMapper;
import com.dmytryk.crud.repository.UserRepository;
import com.dmytryk.crud.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

  private static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RestTemplate restTemplate;

  @Value("${register.success.url}")
  private String url;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Unable to find user email!"));
  }

  @Override
  public UserDto signIn(UserDto userDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            userDto.getEmail(),
            userDto.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    User user = (User) authentication.getPrincipal();
    return INSTANCE.userToUserDto(user);
  }

  @Override
  public UserDto signUp(UserDto userDto) {
    User user = INSTANCE.userDtoToUser(userDto);
    log.info("createUser: about to register a new user with email {}", user.getEmail());

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user = userRepository.save(user);
    log.info("Used with id {} successfully registered", user.getUserId());
    sendRegistrationSuccessMail(user.getEmail());
    return signIn(userDto);
  }

  @Override
  public void signOut() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    log.info("Sign out user with email: {}", user.getEmail());
    SecurityContextHolder.clearContext();
  }

  @Override
  public void sendRegistrationSuccessMail(String email) {
    restTemplate.getForEntity(url + email, String.class);
  }
}
