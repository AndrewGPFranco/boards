package com.agpfdev.board.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsValidation {

    private UtilsValidation() {}

    private static final Pattern patternEmail = Pattern
            .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValidEmail(String email) {
        Matcher matcher = patternEmail.matcher(email);
        return matcher.find();
    }

    /**
     * Caso o usuário tenha 10 anos ou mais, sera possível o cadastro.
     */
    public static boolean isValidAge(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now().minusYears(10);
        return dataNascimento.isBefore(hoje);
    }
}
