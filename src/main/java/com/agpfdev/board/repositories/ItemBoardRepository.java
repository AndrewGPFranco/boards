package com.agpfdev.board.repositories;

import com.agpfdev.board.models.ItemBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemBoardRepository extends JpaRepository<ItemBoard, UUID> {
}
