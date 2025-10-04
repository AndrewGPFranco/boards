package com.agpfdev.board.controllers;

import com.agpfdev.board.dtos.board.InputBoardDTO;
import com.agpfdev.board.dtos.board.OutputBoardDTO;
import com.agpfdev.board.enums.CategoryType;
import com.agpfdev.board.models.User;
import com.agpfdev.board.services.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/v1/board")
    ResponseEntity<String> createBoard(@RequestBody @Valid InputBoardDTO dto, @AuthenticationPrincipal User user) {
        try {
            boardService.createBoard(dto, user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Board criada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/api/v1/board/itens")
    ResponseEntity<Object> getBoard(@AuthenticationPrincipal User user) {
        try {
            List<OutputBoardDTO> boardsByUser = boardService.getBoardsByUser(user);
            return ResponseEntity.ok().body(boardsByUser);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/api/v1/board/categorias")
    ResponseEntity<List<String>> getCategorias() {
        return ResponseEntity.ok().body(Arrays.stream(CategoryType.values()).map(Enum::name).toList());
    }

}
