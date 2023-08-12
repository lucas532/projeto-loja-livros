package com.lucas.projetolojalivros.application.dto;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;

import com.lucas.projetolojalivros.application.dto.input.pais.CriarPaisInput;

public class CriarPaisInputFixture {

    public static CriarPaisInput.CriarPaisInputBuilder builder() {

        return CriarPaisInput.builder()
            .nome(gerarString());
    }
}
