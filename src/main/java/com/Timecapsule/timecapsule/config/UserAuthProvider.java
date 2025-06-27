package com.Timecapsule.timecapsule.config;

import com.Timecapsule.timecapsule.dto.UserDto;
import com.Timecapsule.timecapsule.services.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final UserService userService;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    public String createToken(String email) {
        UserDto user = userService.findByLogin(email);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date now = new Date();
        Date validity = new Date(System.currentTimeMillis() + 30 * 60 * 1000); // 30 minutos

        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(validity)
                .withIssuer(email)
                .withIssuedAt(now)
                .sign(algorithm);
    }

    public String refreshToken(String email) {
        UserDto user = userService.findByLogin(email);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date now = new Date();
        Date validity = new Date(System.currentTimeMillis() + + 7L * 24 * 60 * 60 * 1000); // 7 dias

        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(validity)
                .withIssuer(email)
                .withIssuedAt(now)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        UserDto user = userService.findByLogin(decoded.getIssuer());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
