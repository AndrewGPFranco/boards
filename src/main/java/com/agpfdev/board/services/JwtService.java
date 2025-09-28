package com.agpfdev.board.services;

import com.agpfdev.board.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
public class JwtService {

    @Value("${JWT:default-secret}")
    private String secretKey;

    public String geradorDeTokenJWT(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("board-app")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId().toString())
                    .withClaim("email", user.getEmail())
                    .withExpiresAt(dataExpiracaoDoToken())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            log.error(e.getMessage());
            throw new JWTCreationException("Erro ao criar o token", e);
        } catch (Exception _) {
            throw new RuntimeException("Erro ao criar o token");
        }
    }

    public String validaToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("board-app")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (TokenExpiredException e) {
            log.error("O token: {} não esta mais válido!", token);
            throw new TokenExpiredException("O token não esta mais válido!", e.getExpiredOn());
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
            throw new JWTVerificationException("Ocorreu um erro ao validar o token");
        }
    }

    private Instant dataExpiracaoDoToken() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

}