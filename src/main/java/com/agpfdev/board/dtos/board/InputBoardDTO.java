package com.agpfdev.board.dtos.board;

import com.agpfdev.board.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InputBoardDTO {

    private @NotBlank String titulo;
    private @NotBlank String descricao;
    private User user;

}
