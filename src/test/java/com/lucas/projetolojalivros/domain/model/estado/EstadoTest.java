package com.lucas.projetolojalivros.domain.model.estado;

import static com.lucas.projetolojalivros.helper.ConstraintsHelper.notBeBlank;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.notBeNull;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.validarConstraints;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EstadoTest {

    @Test
    @DisplayName("Criar estado com sucesso")
    void buildComSucesso() {
        assertDoesNotThrow(EstadoFixture::build);
    }

    @Test
    @DisplayName("Criar estado com campos inválidos, deveria lançar exceção!")
    void buildInvalido() {
        var error = assertThrows(ConstraintException.class, EstadoFixture::buildlInvalido);
        validarConstraints(error.getMessage(), 2,
            notBeBlank("nome"),
            notBeNull("pais")
        );
    }
}
