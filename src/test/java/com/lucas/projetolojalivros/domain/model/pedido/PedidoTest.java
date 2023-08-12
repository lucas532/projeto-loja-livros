package com.lucas.projetolojalivros.domain.model.pedido;

import static com.lucas.projetolojalivros.helper.ConstraintsHelper.mustMatch;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.notBeEmpty;
import static com.lucas.projetolojalivros.helper.ConstraintsHelper.validarConstraints;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PedidoTest {

    @Test
    @DisplayName("Criar pedido com sucesso")
    void buildComSucesso() {
        assertDoesNotThrow(PedidoFixture::build);
    }

    @Test
    @DisplayName("Criar pedido com campos inválidos, deveria lançar exceção!")
    void buildInvalido() {
        var error = assertThrows(ConstraintException.class, PedidoFixture::buildlInvalido);
        validarConstraints(error.getMessage(), 2,
            mustMatch("cpfCnpj", "\"([\\d]{11})|([\\d]{14})\""),
            notBeEmpty("itens")
        );
    }
}
