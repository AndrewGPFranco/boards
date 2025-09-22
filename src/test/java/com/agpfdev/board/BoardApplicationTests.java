package com.agpfdev.board;

import com.agpfdev.board.dtos.user.UserRegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.Month;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class BoardApplicationTests {

    protected LocalDate dataNascimentoPadrao = LocalDate.of(2001, Month.JUNE, 28);

	@Test
	void contextLoads() {
	}

    protected UserRegisterDTO getUserRegisterDTO(String email, String username, String numeroTelefone, LocalDate nascimento) {
        return new UserRegisterDTO(
                "Andrew",
                email,
                "password123",
                username,
                "Andrew Nome Completo",
                numeroTelefone,
                nascimento
        );
    }

}
