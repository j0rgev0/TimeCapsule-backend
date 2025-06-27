package com.Timecapsule.timecapsule.controller;

import java.util.List;

import com.Timecapsule.timecapsule.dto.UserDto;
import com.Timecapsule.timecapsule.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Timecapsule.timecapsule.models.User;
import com.Timecapsule.timecapsule.repository.UserRepository;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers(){
    List<UserDto> userDtos = userService.getAll();
    return ResponseEntity.ok(userDtos);
  }

}
