package com.Timecapsule.timecapsule.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

// lo que devuelve el back, lo que le proporcionas al front!!
@Data
@Builder
public class UserDto {
  private UUID id;
  private String name;
  private String email;
  private String avatar;


}
