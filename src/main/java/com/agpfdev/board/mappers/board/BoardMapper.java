package com.agpfdev.board.mappers.board;

import com.agpfdev.board.dtos.AbstractMapper;
import com.agpfdev.board.dtos.board.InputBoardDTO;
import com.agpfdev.board.dtos.board.OutputBoardDTO;
import com.agpfdev.board.mappers.itensBoard.ItemBoardMapper;
import com.agpfdev.board.models.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class BoardMapper extends AbstractMapper<Board, InputBoardDTO, OutputBoardDTO> {

    private final ItemBoardMapper itemBoardMapper;

    @Override
    public Board dtoParaEntidade(InputBoardDTO dto) {
        return Board.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .user(dto.getUser())
                .itensBoard(new ArrayList<>())
                .build();
    }

    @Override
    public OutputBoardDTO entidadeParaDTO(Board board) {
        final String zoneId = "America/Sao_Paulo";

        return OutputBoardDTO.builder()
                .titulo(board.getTitulo())
                .descricao(board.getDescricao())
                .username(board.getUser().getUsername())
                .itensBoard(board.getItensBoard().stream().map(itemBoardMapper::entidadeParaDTO).toList())
                .createdAt(LocalDate.ofInstant(board.getCreatedAt(), ZoneId.of(zoneId)))
                .updatedAt(LocalDate.ofInstant(board.getUpdatedAt(), ZoneId.of(zoneId)))
                .build();
    }
}
