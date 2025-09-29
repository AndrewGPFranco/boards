package com.agpfdev.board.mappers.itensBoard;

import com.agpfdev.board.dtos.AbstractMapper;
import com.agpfdev.board.dtos.itensBoard.InputItemBoardDTO;
import com.agpfdev.board.dtos.itensBoard.OutputItemBoardDTO;
import com.agpfdev.board.models.ItemBoard;
import com.agpfdev.board.models.User;
import org.springframework.stereotype.Component;

@Component
public class ItemBoardMapper extends AbstractMapper<ItemBoard, InputItemBoardDTO, OutputItemBoardDTO> {

    @Override
    public ItemBoard dtoParaEntidade(InputItemBoardDTO dto, User user) {
        return ItemBoard.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .categoria(dto.getCategoryType())
                .board(dto.getBoard())
                .finalizedAt(null)
                .build();
    }

    @Override
    public OutputItemBoardDTO entidadeParaDTO(ItemBoard itemBoard) {
        return new OutputItemBoardDTO();
    }

}
