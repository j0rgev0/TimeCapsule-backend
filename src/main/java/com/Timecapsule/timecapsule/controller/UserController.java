package com.Timecapsule.timecapsule.controller;

import java.util.List;

import com.Timecapsule.timecapsule.dto.CapsulesDtos.CapsuleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Timecapsule.timecapsule.dto.UserDto;
import com.Timecapsule.timecapsule.services.UserService;

import lombok.RequiredArgsConstructor;



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

  @GetMapping("/current")
  public ResponseEntity<UserDto> getCurrentUser(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDto currentUser = (UserDto) authentication.getPrincipal();


    return ResponseEntity.ok(currentUser);
  }

}
