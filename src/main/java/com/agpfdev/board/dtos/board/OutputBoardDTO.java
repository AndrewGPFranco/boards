package com.agpfdev.board.dtos.board;

import com.agpfdev.board.dtos.itensBoard.OutputItemBoardDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record OutputBoardDTO(
        @NotBlank String titulo,
        @NotBlank String descricao,
        @NotBlank String username,
        @NotNull LocalDate createdAt,
        @NotNull LocalDate updatedAt,
        @NotNull List<OutputItemBoardDTO> itensBoard
) {
}
