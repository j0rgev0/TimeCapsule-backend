package com.Timecapsule.timecapsule.dto;

import java.util.UUID;

import lombok.Data;

// Lo que recibes del front!!!! (formulario)
@Data
public class UserCreateDto {
  private UUID id;
  private String name;
  private String email;
  private String password;
  private String avatar;
}
