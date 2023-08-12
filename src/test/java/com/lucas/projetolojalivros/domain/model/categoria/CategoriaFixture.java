package com.lucas.projetolojalivros.domain.model.categoria;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;

public class CategoriaFixture {

    public static Categoria build() {

        return new Categoria(gerarString(),
            gerarString(100));
    }

    public static void buildlInvalido() {

        new Categoria("", "");
    }
}
