package com.lucas.projetolojalivros.application.dto;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;

import com.lucas.projetolojalivros.application.dto.input.categoria.CriarCategoriaInput;

public class CriarCategoriaInputFixture {

    public static CriarCategoriaInput.CriarCategoriaInputBuilder builder() {

        return CriarCategoriaInput.builder()
            .nome(gerarString())
            .descricao(gerarString(100));
    }
}
