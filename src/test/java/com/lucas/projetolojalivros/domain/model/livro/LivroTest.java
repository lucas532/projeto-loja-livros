package com.lucas.projetolojalivros.domain.model.livro;

import static com.lucas.projetolojalivros.helper.ConstraintsHelper.mustBeGreaterThan;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.notBeBlank;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.notBeNull;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.sizeMustBeBetween;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.validarConstraints;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LivroTest {

    @Test
    @DisplayName("Criar livro com sucesso")
    void buildComSucesso() {
        assertDoesNotThrow(LivroFixture::build);
    }

    @Test
    @DisplayName("Criar livro com campos inválidos, deveria lançar exceção!")
    void buildInvalido() {
        var error = assertThrows(ConstraintException.class, LivroFixture::buildlInvalido);
        validarConstraints(error.getMessage(), 8,
            notBeBlank("titulo"),
            sizeMustBeBetween("resumo", "0", "500"),
            mustBeGreaterThan("preco", "20.00"),
            mustBeGreaterThan("numeroPaginas", "100"),
            notBeBlank("isbn"),
            notBeNull("dataPublicacao"),
            notBeNull("autor"),
            notBeNull("categoria")
        );
    }
}
