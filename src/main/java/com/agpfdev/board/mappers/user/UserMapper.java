package com.agpfdev.board.mappers.user;

import com.agpfdev.board.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.agpfdev.board.dtos.user.UserRegisterDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User dtoParaEntidade(UserRegisterDTO dto) {
        return new User(dto.nome(), dto.nomeCompleto(), dto.email(), bCryptPasswordEncoder.encode(dto.senha()),
                dto.numeroTelefone(), dto.dataNascimento(), dto.username());
    }

}
