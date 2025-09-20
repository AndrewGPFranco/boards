package com.agpfdev.board.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsValidation {

    private static final Pattern pattern = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");

    public static void validaEmail(String email) {
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) throw new IllegalArgumentException("""
                    O email informado não esta no padrão necessário!
                    Padrão correto: 'email@email.com'
                """);
    }

    /**
     * Caso o usuário tenha 10 anos ou mais, sera possível o cadastro.
     */
    public static void validaIdadeUsuario(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now().minusYears(10);

        if (!dataNascimento.isBefore(hoje))
            throw new IllegalArgumentException("Para utilizar o sistema é necessário ter ao menos 10 anos de idade!");
    }
}
