package com.lucas.projetolojalivros.adapter.primary.error;

import static java.util.Arrays.stream;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@NoArgsConstructor(access = PRIVATE)
class ErroSolicitacaoResponseFixture {

    static ErroSolicitacaoResponse build(Collection<FieldError> fieldErrors) {
        return ErroSolicitacaoResponse
            .builder()
            .mensagem("Erro de solicitação")
            .erros(buildErros(fieldErrors))
            .build();
    }

    static ErroSolicitacaoResponse build(ConstraintException constraintException) {
        return ErroSolicitacaoResponse
            .builder()
            .mensagem("Erro de validação")
            .erros(buildErros(constraintException))
            .build();
    }

    private static Collection<ErroValidacao> buildErros(Collection<FieldError> fieldErrors) {

        return fieldErrors.stream()
            .map(error -> buildErroValidacao(
                error.getField(),
                error.getDefaultMessage()
            )).collect(Collectors.toList());
    }

    private static Collection<ErroValidacao> buildErros(ConstraintException constraintException) {
        var mensagemErroSplit = "Erro ao validar constraints: ";

        var errors = stream(constraintException.getMessage().split(mensagemErroSplit))
            .reduce((prefixoErro, parametros) -> parametros)
            .orElseThrow()
            .split(",");

        return Arrays.stream(errors)
            .map(error -> buildErroValidacao(
                    substringBefore(error, ":"),
                    substringAfter(error, ": ")
                )
            ).collect(Collectors.toList());
    }

    private static ErroValidacao buildErroValidacao(String campo, String mensagem) {

        return ErroValidacao
            .builder()
            .campo(campo)
            .mensagem(mensagem)
            .build();
    }
}
