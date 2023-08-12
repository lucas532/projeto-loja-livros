package com.lucas.projetolojalivros.helper;

import static java.util.Arrays.stream;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;

@NoArgsConstructor(access = PRIVATE)
public class ConstraintsHelper {

    private static final String SEPARADOR_ERROR_CONSTRAINT = ", ";
    private static final String ERRO_VALIDAR_CONSTRAINTS = "Erro ao validar constraints: ";

    public static void validarConstraints(String exception, int quantity, String... messages) {
        Assertions.assertTrue(exception.contains(ERRO_VALIDAR_CONSTRAINTS));
        validaMensagensDeExcecao(exception, quantity, messages);
    }

    private static void validaMensagensDeExcecao(String exception, int quantity,
        String... messages) {
        assertThat(mensagensDeExcecao(exception))
            .hasSize(quantity)
            .extracting(String::valueOf)
            .containsExactlyInAnyOrder(messages);
    }

    private static String[] mensagensDeExcecao(String message) {
        return stream(message.split(ERRO_VALIDAR_CONSTRAINTS))
            .reduce((prefixoErro, parametros) -> parametros)
            .orElseThrow()
            .split(SEPARADOR_ERROR_CONSTRAINT);
    }

    public static String mustBeGreaterThan(String name, String minValue) {
        return constraintMessage(name, "must be greater than or equal to " + minValue);
    }

    public static String sizeMustBeBetween(String name, String minValue, String maxValue) {
        return constraintMessage(name, "size must be between " + minValue + " and " + maxValue);
    }

    public static String notBeNull(String value) {
        return constraintMessage(value, "must not be null");
    }

    public static String notBeBlank(String value) {
        return constraintMessage(value, "must not be blank");
    }

    public static String notBeEmpty(String value) {
        return constraintMessage(value, "must not be empty");
    }

    public static String mustMatch(String value, String pattern) {
        return constraintMessage(value, String.format("must match %s", pattern));
    }

    public static String beZeroOrPositive(String value) {
        return constraintMessage(value, "must be greater than or equal to 0");
    }

    public static String bePositive(String value) {
        return constraintMessage(value, "must be greater than 0");
    }

    public static String mustBeEmail(String value) {
        return constraintMessage(value, "must be a well-formed email address");
    }

    private static String constraintMessage(String value, String message) {
        return String.format("%s: %s", value, message);
    }
}
