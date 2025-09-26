package com.agpfdev.board.controllers;

import com.agpfdev.board.dtos.user.UserRegisterDTO;
import com.agpfdev.board.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
