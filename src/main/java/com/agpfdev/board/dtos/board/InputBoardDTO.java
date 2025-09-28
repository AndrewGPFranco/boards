package com.agpfdev.board.dtos.board;

import jakarta.validation.constraints.NotBlank;

public record InputBoardDTO(
        @NotBlank String titulo,
        @NotBlank String descricao
) {
}
