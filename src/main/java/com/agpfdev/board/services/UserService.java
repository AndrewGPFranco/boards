package com.agpfdev.board.services;

import com.agpfdev.board.dtos.user.UserRegisterDTO;
import com.agpfdev.board.mappers.user.UserMapper;
import com.agpfdev.board.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public void registrarUsuario(UserRegisterDTO dto) {
        try {
            userRepository.save(userMapper.dtoParaEntidade(dto));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Ocorreu um erro ao realizar o cadastro!");
        }
    }

}
