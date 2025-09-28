package com.agpfdev.board.facades;

import com.agpfdev.board.mappers.board.BoardMapper;
import com.agpfdev.board.mappers.itensBoard.ItemBoardMapper;
import com.agpfdev.board.mappers.user.UserMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class MapperFacade {

    private final UserMapper userMapper;
    private final BoardMapper boardMapper;
    private final ItemBoardMapper itemBoardMapper;

}
