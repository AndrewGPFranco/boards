package com.agpfdev.board.services;

import com.agpfdev.board.dtos.user.UserRegisterDTO;
import com.agpfdev.board.mappers.user.UserMapper;
import com.agpfdev.board.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public void registrarUsuario(UserRegisterDTO dto) {
        try {
            validaInput(dto);
            userRepository.save(userMapper.dtoParaEntidade(dto));
        } catch (DataIntegrityViolationException | IllegalArgumentException ex) {
            log.error(ex.getMessage());
            throw new DataIntegrityViolationException(ex.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Ocorreu um erro ao realizar o cadastro!");
        }
    }

    private final Consumer<String> throwError = campo -> {
        throw new IllegalArgumentException(campo);
    };

    private void validaInput(UserRegisterDTO dto) {
        userRepository.findByEmail(dto.email()).ifPresent(_ ->
                throwError.accept(String.format("O email %s já esta em uso", dto.email())));

        userRepository.findByUsername(dto.username()).ifPresent(_ ->
                throwError.accept(String.format("O username %s já esta em uso!", dto.username())));

        userRepository.findByNumeroTelefone(dto.numeroTelefone()).ifPresent(_ ->
                throwError.accept(String.format("O número de telefone %s já esta em uso!", dto.numeroTelefone())));
    }

}
