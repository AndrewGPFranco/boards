package com.agpfdev.board.services;

import com.agpfdev.board.dtos.board.InputBoardDTO;
import com.agpfdev.board.facades.MapperFacade;
import com.agpfdev.board.models.Board;
import com.agpfdev.board.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final MapperFacade mapperFacade;
    private final BoardRepository boardRepository;

    public void createBoard(InputBoardDTO inputBoardDTO) {
        try {
            Board board = mapperFacade.getBoardMapper().dtoParaEntidade(inputBoardDTO);
            boardRepository.save(board);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Ocorreu um erro ao criar a board, tente novamente!");
        }
    }

}
