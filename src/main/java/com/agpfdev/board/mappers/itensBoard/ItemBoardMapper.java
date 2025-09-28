package com.agpfdev.board.mappers.itensBoard;

import com.agpfdev.board.dtos.AbstractMapper;
import com.agpfdev.board.dtos.itensBoard.InputItemBoardDTO;
import com.agpfdev.board.dtos.itensBoard.OutputItemBoardDTO;
import com.agpfdev.board.models.ItemBoard;
import org.springframework.stereotype.Component;

@Component
public class ItemBoardMapper extends AbstractMapper<ItemBoard, InputItemBoardDTO, OutputItemBoardDTO> {

    @Override
    public ItemBoard dtoParaEntidade(InputItemBoardDTO dto) {
        return null;
    }

    @Override
    public OutputItemBoardDTO entidadeParaDTO(ItemBoard itemBoard) {
        return null;
    }

}
