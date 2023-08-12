package com.lucas.projetolojalivros.domain.model.autor;

import static com.lucas.projetolojalivros.helper.ConstraintsHelper.mustBeEmail;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.notBeBlank;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.sizeMustBeBetween;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.validarConstraints;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutorTest {

    @Test
    @DisplayName("Criar autor com sucesso")
    void buildComSucesso() {
        assertDoesNotThrow(AutorFixture::build);
    }

    @Test
    @DisplayName("Criar autor com campos inválidos, deveria lançar exceção!")
    void buildInvalido() {
        var error = assertThrows(ConstraintException.class, AutorFixture::buildlInvalido);
        validarConstraints(error.getMessage(), 3,
            notBeBlank("nome"),
            mustBeEmail("email"),
            sizeMustBeBetween("descricao", "0", "300")
        );
    }
}
