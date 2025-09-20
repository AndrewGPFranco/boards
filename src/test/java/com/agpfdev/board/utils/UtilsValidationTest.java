package com.agpfdev.board.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UtilsValidationTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("validEmail")
    void methodSourceValidEmail(String email, boolean isValid) {
        boolean result = UtilsValidation.isValidEmail(email);
        assertEquals(isValid, result);
    }

    public static Stream<Arguments> validEmail() {
        return Stream.of(
                Arguments.of("andrewgo@gmail.com", true),
                Arguments.of("andrewgogmail.com", false),
                Arguments.of("andrewgo@gmailcom", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validAge")
    void methodSourceValidAge(LocalDate dateBirth, boolean isValid) {
        boolean result = UtilsValidation.isValidAge(dateBirth);
        assertEquals(isValid, result);
    }

    public static Stream<Arguments> validAge() {
        return Stream.of(
                Arguments.of(LocalDate.now(), false),
                Arguments.of(LocalDate.now().minusYears(11), true),
                Arguments.of(LocalDate.now().minusYears(9), false)
        );
    }

}