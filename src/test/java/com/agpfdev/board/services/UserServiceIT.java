package com.agpfdev.board.services;

import com.agpfdev.board.BoardApplicationTests;
import com.agpfdev.board.dtos.user.UserRegisterDTO;
import com.agpfdev.board.mappers.user.UserMapper;
import com.agpfdev.board.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;

class UserServiceIT extends BoardApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Salva usuário com sucesso")
    void testRegisterUser() {
        UserRegisterDTO dto = getUserRegisterDTO("success@gmail.com", "success",
                "11972238465", this.dataNascimentoPadrao);
        userService.registrarUsuario(dto);
    }

    @Test
    void testRegisterUserComUsernameJaEmUso() {
        UserRegisterDTO dto = getUserRegisterDTO("email@gmail.com", "andrewgo",
                "11972938465", this.dataNascimentoPadrao);
        userService.registrarUsuario(dto);

        UserRegisterDTO dtoUsernameRepetido = getUserRegisterDTO("email2@gmail.com", "andrewgo",
                "11972938321", this.dataNascimentoPadrao);
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> userService.registrarUsuario(dtoUsernameRepetido));
        Assertions.assertEquals("O username informado já está em uso!", runtimeException.getMessage());

        UserRegisterDTO dtoEmailRepetido = getUserRegisterDTO("email@gmail.com", "andrew",
                "11972938339", this.dataNascimentoPadrao);
        RuntimeException runtimeException2 = Assertions.assertThrows(RuntimeException.class, () -> userService.registrarUsuario(dtoEmailRepetido));
        Assertions.assertEquals("O email informado já está em uso!", runtimeException2.getMessage());

        UserRegisterDTO dtoNumeroTelefoneRepetido = getUserRegisterDTO("email1@gmail.com", "andrew",
                "11972938465", this.dataNascimentoPadrao);
        RuntimeException runtimeException3 = Assertions.assertThrows(RuntimeException.class, () -> userService.registrarUsuario(dtoNumeroTelefoneRepetido));
        Assertions.assertEquals("O número de telefone informado já está em uso!", runtimeException3.getMessage());

        UserRegisterDTO dtoEmailFormatoIncorreto = getUserRegisterDTO("email1gmail.com", "andrew",
                "11972938465", this.dataNascimentoPadrao);
        RuntimeException runtimeException4 = Assertions.assertThrows(RuntimeException.class, () -> userService.registrarUsuario(dtoEmailFormatoIncorreto));
        Assertions.assertEquals("""
                    O email informado não esta no padrão necessário!
                    Padrão correto: 'email@email.com'
                """, runtimeException4.getMessage());

        UserRegisterDTO dtoIdadeAbaixoDoPermitido = getUserRegisterDTO("email1@gmail.com", "andrew",
                "11972938465", LocalDate.of(2018, Month.JUNE, 28));
        RuntimeException runtimeException5 = Assertions.assertThrows(RuntimeException.class, () -> userService.registrarUsuario(dtoIdadeAbaixoDoPermitido));
        Assertions.assertEquals("Para utilizar o sistema é necessário ter ao menos 10 anos de idade!", runtimeException5.getMessage());
    }

}