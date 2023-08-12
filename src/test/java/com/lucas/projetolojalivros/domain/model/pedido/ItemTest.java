package com.lucas.projetolojalivros.domain.model.pedido;

import static com.lucas.projetolojalivros.helper.ConstraintsHelper.mustBeGreaterThan;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.notBeNull;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.validarConstraints;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    @DisplayName("Criar item com sucesso")
    void buildComSucesso() {
        assertDoesNotThrow(ItemFixture::build);
    }

    @Test
    @DisplayName("Criar item com campos inválidos, deveria lançar exceção!")
    void buildInvalido() {
        var error = assertThrows(ConstraintException.class, ItemFixture::buildlInvalido);
        validarConstraints(error.getMessage(), 2,
            mustBeGreaterThan("quantidade", "1"),
            notBeNull("livro")
        );
    }
}
