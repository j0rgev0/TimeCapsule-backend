package com.Timecapsule.timecapsule.models.mappers;

import com.Timecapsule.timecapsule.dto.UserCreateDto;
import com.Timecapsule.timecapsule.dto.UserDto;
import com.Timecapsule.timecapsule.models.User;
import org.springframework.stereotype.Component;
/*
 * Esta clase se encarga solo de convertir entre entidades y DTOs.👌
  👉 Así no ensucias los services con lógica que no les corresponde.
 */
@Component
public class UserMapper {
  // Para convertir un User (entidad) en un UserDTO (salida al frontend)
  public UserDto toDto(User user) {
    return UserDto.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .avatar(user.getAvatar())
        .build();
  }
  // Para convertir un UserCreateDTO (entrada desde frontend) en User
  public User fromDto(UserCreateDto userDto) {
    return User.builder()
        .id(userDto.getId())
        .name(userDto.getName())
        .email(userDto.getEmail())
        .avatar(userDto.getAvatar())
        .build();
  }
}
