package com.lucas.projetolojalivros.application.dto;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarLong;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;

import com.lucas.projetolojalivros.application.dto.input.estado.CriarEstadoInput;

public class CriarEstadoInputFixture {

    public static CriarEstadoInput.CriarEstadoInputBuilder builder() {

        return CriarEstadoInput.builder()
            .nome(gerarString())
            .idPais(gerarLong());
    }
}
