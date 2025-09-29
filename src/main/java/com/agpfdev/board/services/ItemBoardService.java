package com.agpfdev.board.services;

import com.agpfdev.board.dtos.itensBoard.InputItemBoardDTO;
import com.agpfdev.board.exceptions.NotFoundException;
import com.agpfdev.board.mappers.itensBoard.ItemBoardMapper;
import com.agpfdev.board.models.Board;
import com.agpfdev.board.models.ItemBoard;
import com.agpfdev.board.repositories.BoardRepository;
import com.agpfdev.board.repositories.ItemBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ItemBoardService {

    private final BoardRepository boardRepository;
    private final ItemBoardMapper itemBoardMapper;
    private final ItemBoardRepository itemBoardRepository;

    public void createNewItem(InputItemBoardDTO dto) {
        try {
            Board board = boardRepository.findById(dto.getIdBoard()).orElseThrow(() ->
                    new NotFoundException(String.format("Nenhuma board com o ID: %s informado!", dto.getIdBoard())));

            dto.setBoard(board);

            ItemBoard itemBoard = itemBoardMapper.dtoParaEntidade(dto, null);

            ItemBoard itemSaved = itemBoardRepository.save(itemBoard);

            if (board.getItensBoard() == null) board.setItensBoard(new ArrayList<>());

            board.getItensBoard().add(itemSaved);

            boardRepository.save(board);
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um erro ao criar um novo item para o quadro!");
        }
    }

}
