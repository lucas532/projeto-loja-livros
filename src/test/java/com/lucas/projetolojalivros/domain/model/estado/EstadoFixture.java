package com.lucas.projetolojalivros.domain.model.estado;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarObjeto;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;

import com.lucas.projetolojalivros.domain.model.pais.Pais;

public class EstadoFixture {

    public static Estado build() {

        return new Estado(gerarString(), gerarObjeto(Pais.class));
    }

    public static void buildlInvalido() {

        new Estado("", null);
    }
}
