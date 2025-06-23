package com.Timecapsule.timecapsule.services;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Timecapsule.timecapsule.dto.UserCreateDto;
import com.Timecapsule.timecapsule.dto.UserDto;
import com.Timecapsule.timecapsule.models.User;
import com.Timecapsule.timecapsule.models.mappers.UserMapper;
import com.Timecapsule.timecapsule.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserDto createUser(UserCreateDto dto) {
    User user = userMapper.fromDto(dto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return userMapper.toDto(user);
  }

  public UserDto getUser(UUID id) {
    User user = userRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    return userMapper.toDto(user);
  }
  
}
