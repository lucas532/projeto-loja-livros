package com.lucas.projetolojalivros.domain.model.pedido;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarInteger;

import com.lucas.projetolojalivros.domain.model.livro.Livro;
import com.lucas.projetolojalivros.domain.model.livro.LivroFixture;
import java.math.BigDecimal;

public class ItemFixture {

    public static Item build() {

        return new Item(gerarInteger(), LivroFixture.build());
    }

    public static Item buildComQuantidadeELivro(Integer quantidade, Livro livro) {

        return new Item(quantidade, livro);
    }

    public static Item buildComValorTotal(BigDecimal totalPedido) {

        return new Item(gerarInteger(), LivroFixture.buildComValorTotal(totalPedido));
    }

    public static void buildlInvalido() {

        new Item(0, null);
    }
}
