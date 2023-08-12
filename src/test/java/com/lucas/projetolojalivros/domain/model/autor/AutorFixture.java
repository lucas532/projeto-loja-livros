package com.lucas.projetolojalivros.domain.model.autor;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarStringEmail;

public class AutorFixture {

    public static Autor build() {

        return new Autor(gerarString(),
            gerarStringEmail(),
            gerarString(100));
    }

    public static void buildlInvalido() {

        new Autor("",
            "emailInvalido",
            gerarString(350));
    }
}
