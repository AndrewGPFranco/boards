package com.agpfdev.board.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRegisterDTO(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String senha,
        @NotBlank String username,
        @NotBlank String nomeCompleto,
        @NotBlank String numeroTelefone,
        @NotNull LocalDate dataNascimento
) {}
