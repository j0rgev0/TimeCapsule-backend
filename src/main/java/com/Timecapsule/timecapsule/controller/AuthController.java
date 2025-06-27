package com.Timecapsule.timecapsule.controller;

import com.Timecapsule.timecapsule.config.UserAuthProvider;
import com.Timecapsule.timecapsule.dto.CredentialsDto;
import com.Timecapsule.timecapsule.dto.SignUpDto;
import com.Timecapsule.timecapsule.dto.UserDto;
import com.Timecapsule.timecapsule.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody CredentialsDto credentialsDto,
            HttpServletResponse response
    ) {
        UserDto user = userService.login(credentialsDto);
        String accessToken = userAuthProvider.createToken(user.getEmail());
        String refreshToken = userAuthProvider.refreshToken(user.getEmail());

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false) // ⚠️ true en producción (HTTPS)
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // 7 días
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", refreshCookie.toString());

        Map<String, Object> body = new HashMap<>();
        body.put("user", user);
        body.put("accessToken", accessToken);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody SignUpDto signUpDto,
            HttpServletResponse response
    ) {
        UserDto user = userService.register(signUpDto);
        String accessToken = userAuthProvider.createToken(user.getEmail());
        String refreshToken = userAuthProvider.refreshToken(user.getEmail());

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false) // ⚠️ true en producción (HTTPS)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", refreshCookie.toString());

        Map<String, Object> body = new HashMap<>();
        body.put("user", user);
        body.put("accessToken", accessToken);

        return ResponseEntity.created(URI.create("/api/users/" + user.getId())).body(body);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<?> refreshToken(
            @CookieValue(name = "refreshToken", required = true) String refreshToken
    ) {
        System.out.println("Refresh token cookie value: " + refreshToken);

        try {
            Authentication auth = userAuthProvider.validateToken(refreshToken);
            UserDto user = (UserDto) auth.getPrincipal();
            String newAccessToken = userAuthProvider.createToken(user.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("accessToken", newAccessToken);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid or expired refresh token");
        }
    }

    @ExceptionHandler(MissingRequestCookieException.class)
    public ResponseEntity<String> handleMissingCookie(MissingRequestCookieException ex) {
        if ("refreshToken".equals(ex.getCookieName())) {
            return ResponseEntity.status(401).body("Refresh token cookie is missing");
        }
        return ResponseEntity.status(400).body("Required cookie missing");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // Borra la cookie del refresh token con el mismo path que se creó
        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false) // ⚠️ true en producción (HTTPS)
                .path("/")  // coincide con creación
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", deleteCookie.toString());
        return ResponseEntity.ok("Logged out");
    }
}
