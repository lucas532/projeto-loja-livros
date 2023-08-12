package com.lucas.projetolojalivros.application.dto;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarStringEmail;

import com.lucas.projetolojalivros.application.dto.input.autor.CriarAutorInput;

public class CriarAutorInputFixture {

    public static CriarAutorInput.CriarAutorInputBuilder builder() {

        return CriarAutorInput.builder()
            .nome(gerarString())
            .email(gerarStringEmail())
            .descricao(gerarString(100));
    }
}
