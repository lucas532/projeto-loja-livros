package com.lucas.projetolojalivros.domain.model.pedido;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarStringNumerico;
import static java.util.Collections.emptyList;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class PedidoFixture {

    public static Pedido build() {

        return new Pedido(gerarStringNumerico(11), List.of(ItemFixture.build()));
    }

    public static Pedido buildComCpfEItens(String cpf, Collection<Item> itens) {

        return new Pedido(cpf, itens);
    }

    public static Pedido buildComCpfEValorTotal(String cpf, BigDecimal totalPedido) {
        return new Pedido(cpf, List.of(ItemFixture.buildComValorTotal(totalPedido)));
    }

    public static void buildlInvalido() {

        new Pedido(gerarString(9), emptyList());
    }
}
