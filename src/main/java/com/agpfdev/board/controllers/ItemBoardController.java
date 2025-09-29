package com.agpfdev.board.controllers;

import com.agpfdev.board.dtos.itensBoard.InputItemBoardDTO;
import com.agpfdev.board.services.ItemBoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemBoardController {

    private final ItemBoardService itemBoardService;

    @PostMapping("/api/v1/item-board")
    ResponseEntity<String> addNewItem(@RequestBody @Valid InputItemBoardDTO dto) {
        try {
            itemBoardService.createNewItem(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("O item foi adicionado ao quadro!");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
