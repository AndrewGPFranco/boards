package com.agpfdev.board.controllers;

import com.agpfdev.board.dtos.ResponseAPI;
import com.agpfdev.board.dtos.user.LoginRequestDTO;
import com.agpfdev.board.dtos.user.UserRegisterDTO;
import com.agpfdev.board.models.User;
import com.agpfdev.board.services.JwtService;
import com.agpfdev.board.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authManager;

    @PostMapping("/open/v1/auth/login")
    ResponseEntity<ResponseAPI> login(@RequestBody LoginRequestDTO dto) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(),
                    dto.password());

            Authentication auth = this.authManager.authenticate(usernamePassword);
            String token = jwtService.geradorDeTokenJWT((User) auth.getPrincipal());

            return ResponseEntity.ok().body(new ResponseAPI(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new ResponseAPI(e.getMessage()));
        } catch (InternalAuthenticationServiceException _) {
            return ResponseEntity.badRequest().body(new ResponseAPI("Email digitado não encontrado no sistema!"));
        } catch (Exception _) {
            return ResponseEntity.badRequest().body(new ResponseAPI("Ocorreu um erro ao processar o login!"));
        }
    }

    @PostMapping("/open/v1/auth/registrar-usuario")
    ResponseEntity<String> salvarUsuario(@Valid @RequestBody UserRegisterDTO usuario) {
        try {
            userService.registrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
        } catch (IllegalArgumentException | DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception _) {
            return ResponseEntity.internalServerError()
                    .body("Ocorreu um erro ao registrar-se, confira os dados e tente novamente!");
        }
    }

}
