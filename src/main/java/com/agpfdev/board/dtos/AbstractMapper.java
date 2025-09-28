package com.agpfdev.board.dtos;

public abstract class AbstractMapper<ENTIDADE, INPUTDTO, OUTPUTDTO> {

    public abstract ENTIDADE dtoParaEntidade(INPUTDTO dto);

    public abstract OUTPUTDTO entidadeParaDTO(ENTIDADE entidade);

}
