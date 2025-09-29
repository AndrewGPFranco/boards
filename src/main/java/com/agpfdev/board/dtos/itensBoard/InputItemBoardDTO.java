package com.agpfdev.board.dtos.itensBoard;

import com.agpfdev.board.enums.CategoryType;
import com.agpfdev.board.models.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class InputItemBoardDTO {

    private @NotBlank String titulo;
    private @NotBlank String descricao;
    private @NotNull CategoryType categoryType;
    private @NotNull UUID idBoard;
    private @Setter Board board;

}
