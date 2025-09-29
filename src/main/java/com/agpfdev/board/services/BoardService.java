package com.agpfdev.board.services;

import com.agpfdev.board.dtos.board.InputBoardDTO;
import com.agpfdev.board.dtos.board.OutputBoardDTO;
import com.agpfdev.board.facades.MapperFacade;
import com.agpfdev.board.models.Board;
import com.agpfdev.board.models.User;
import com.agpfdev.board.repositories.BoardRepository;
import com.agpfdev.board.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final MapperFacade mapperFacade;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public void createBoard(InputBoardDTO inputBoardDTO, User user) {
        try {
            User managedUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Board board = mapperFacade.getBoardMapper().dtoParaEntidade(inputBoardDTO, managedUser);

            managedUser.setBoard(board);
            boardRepository.save(board);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Ocorreu um erro ao criar a board, tente novamente!");
        }
    }

    public OutputBoardDTO getBoardByUser(User user) {
        Board board = user.getBoard();
        return mapperFacade.getBoardMapper().entidadeParaDTO(board);
    }
}
