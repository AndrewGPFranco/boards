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

import java.util.ArrayList;
import java.util.List;

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

            if (managedUser.getBoards() == null) managedUser.setBoards(new ArrayList<>());

            managedUser.getBoards().add(board);
            boardRepository.save(board);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Ocorreu um erro ao criar a board, tente novamente!");
        }
    }

    public List<OutputBoardDTO> getBoardsByUser(User user) {
        List<Board> boards = user.getBoards();
        return boards.stream().map(board -> mapperFacade.getBoardMapper().entidadeParaDTO(board)).toList();
    }
}
