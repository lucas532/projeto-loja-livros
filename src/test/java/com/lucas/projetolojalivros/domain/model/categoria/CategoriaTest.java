package com.lucas.projetolojalivros.domain.model.categoria;

import static com.lucas.projetolojalivros.helper.ConstraintsHelper.notBeBlank;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.validarConstraints;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoriaTest {

    @Test
    @DisplayName("Criar categoria com sucesso")
    void buildComSucesso() {
        assertDoesNotThrow(CategoriaFixture::build);
    }

    @Test
    @DisplayName("Criar categoria com campos inválidos, deveria lançar exceção!")
    void buildInvalido() {
        var error = assertThrows(ConstraintException.class, CategoriaFixture::buildlInvalido);
        validarConstraints(error.getMessage(), 1,
            notBeBlank("nome")
        );
    }
}
