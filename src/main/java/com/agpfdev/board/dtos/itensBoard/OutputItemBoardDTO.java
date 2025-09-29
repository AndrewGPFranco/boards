package com.agpfdev.board.dtos.itensBoard;

import com.agpfdev.board.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record OutputItemBoardDTO(
        @NotNull UUID id,
        @NotBlank String titulo,
        @NotBlank String descricao,
        @NotNull CategoryType categoria,
        @NotNull LocalDate createdAt,
        @NotNull LocalDate updateAt,
        @NotNull LocalDate finalizedAt
) {
}
