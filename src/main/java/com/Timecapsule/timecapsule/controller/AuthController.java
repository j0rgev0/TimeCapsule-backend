package com.Timecapsule.timecapsule.controller;

import com.Timecapsule.timecapsule.config.UserAuthProvider;
import com.Timecapsule.timecapsule.dto.CredentialsDto;
import com.Timecapsule.timecapsule.dto.SignUpDto;
import com.Timecapsule.timecapsule.dto.UserDto;
import com.Timecapsule.timecapsule.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/api/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto user = userService.login(credentialsDto);

        user.setToken(userAuthProvider.createToken(user.getLogin()));
        return ResponseEntity.ok(user);

    }

    @PostMapping("/api/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto user = userService.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user.getLogin()));
        return ResponseEntity.created(URI.create("/api/users" + user.getId())).body(user);
    }

}
