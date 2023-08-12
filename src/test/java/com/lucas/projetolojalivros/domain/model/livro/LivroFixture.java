package com.lucas.projetolojalivros.domain.model.livro;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarBigDecimal;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarData;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarInteger;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarObjeto;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;
import static java.time.LocalDate.now;

import com.lucas.projetolojalivros.domain.model.autor.Autor;
import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import java.math.BigDecimal;

public class LivroFixture {

    public static Livro build() {

        return new Livro(gerarString(), gerarString(300), gerarString(),
            gerarBigDecimal(20.0, 100.0), gerarInteger(100, 1000),
            gerarString(), gerarData(now(), now().plusDays(7)), gerarObjeto(
            Autor.class), gerarObjeto(Categoria.class));
    }

    public static Livro buildComValorTotal(BigDecimal totalPedido) {

        return new Livro(gerarString(), gerarString(300), gerarString(),
            totalPedido, gerarInteger(100, 1000),
            gerarString(), gerarData(now(), now().plusDays(7)), gerarObjeto(
            Autor.class), gerarObjeto(Categoria.class));
    }

    public static void buildlInvalido() {

        new Livro("", gerarString(600), "", gerarBigDecimal(0.0, 19.0), gerarInteger(0, 99),
            "", null, null, null);
    }
}
