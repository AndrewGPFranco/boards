package com.agpfdev.board.services;

import com.agpfdev.board.dtos.user.UserRegisterDTO;
import com.agpfdev.board.mappers.user.UserMapper;
import com.agpfdev.board.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private static final Map<String, String> CAMPOS_MENSAGEM = Map.of(
            "users_email_key", "O email informado já está em uso!",
            "users_username_key", "O username informado já está em uso!",
            "users_numero_telefone_key", "O número de telefone informado já está em uso!"
    );

    public void registrarUsuario(UserRegisterDTO dto) {
        try {
            userRepository.save(userMapper.dtoParaEntidade(dto));
        } catch (DataIntegrityViolationException ex) {
            log.error(ex.getMessage());
            String mensagemErro = recuperaCampoJaUtilizado(ex.getMessage());
            throw new DataIntegrityViolationException(mensagemErro);
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
            String mensagemErro = recuperaCampoJaUtilizado(iae.getMessage());
            throw new IllegalArgumentException(mensagemErro);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Ocorreu um erro ao realizar o cadastro!");
        }
    }

    /**
     * Responsável por verificar qual o campo que já foi utilizado, passando uma resposta certeira ao usuário
     *
     * @return mensagem formatada informando o campo que já foi utilizado!
     */
    protected String recuperaCampoJaUtilizado(String mensagemException) {
        log.error(mensagemException);

        for (Map.Entry<String, String> entry : CAMPOS_MENSAGEM.entrySet()) {
            if (mensagemException.contains(entry.getKey()))
                return entry.getValue();
        }

        if (mensagemException.contains("Padrão correto:") || mensagemException.contains("10 anos de idade"))
            return mensagemException;

        return "Ocorreu um erro ao registrar o usuário, verifique os dados e tente novamente!";
    }
}
