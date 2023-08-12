package com.lucas.projetolojalivros.domain.model.pais;

import static com.lucas.projetolojalivros.helper.ConstraintsHelper.notBeBlank;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.validarConstraints;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaisTest {

    @Test
    @DisplayName("Criar país com sucesso")
    void buildComSucesso() {
        assertDoesNotThrow(PaisFixture::build);
    }

    @Test
    @DisplayName("Criar país com campos inválidos, deveria lançar exceção!")
    void buildInvalido() {
        var error = assertThrows(ConstraintException.class, PaisFixture::buildlInvalido);
        validarConstraints(error.getMessage(), 1,
            notBeBlank("nome")
        );
    }
}
