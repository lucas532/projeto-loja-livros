package com.lucas.projetolojalivros.domain.model.pais;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;

public class PaisFixture {

    public static Pais build() {

        return new Pais(gerarString());
    }

    public static void buildlInvalido() {

        new Pais("");
    }
}
