package com.Timecapsule.timecapsule.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {


  private UUID id;
  private String name;
  private String email;
  private String avatar;
  private String token;


}
