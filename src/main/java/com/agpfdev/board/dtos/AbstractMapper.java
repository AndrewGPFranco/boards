package com.agpfdev.board.dtos;

import com.agpfdev.board.models.User;

public abstract class AbstractMapper<ENTIDADE, INPUTDTO, OUTPUTDTO> {

    public abstract ENTIDADE dtoParaEntidade(INPUTDTO dto, User user);

    public abstract OUTPUTDTO entidadeParaDTO(ENTIDADE entidade);

}
