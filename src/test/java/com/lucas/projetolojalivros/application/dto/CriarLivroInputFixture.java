package com.lucas.projetolojalivros.application.dto;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarBigDecimal;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarData;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarInteger;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarLong;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;
import static java.time.LocalDate.now;

import com.lucas.projetolojalivros.application.dto.input.livro.CriarLivroInput;

public class CriarLivroInputFixture {

    public static CriarLivroInput.CriarLivroInputBuilder builder() {

        return CriarLivroInput.builder()
            .titulo(gerarString())
            .resumo(gerarString(300))
            .sumario(gerarString())
            .preco(gerarBigDecimal(20.00, 100.00))
            .numeroPaginas(gerarInteger(100, 1000))
            .isbn(gerarString())
            .dataPublicacao(gerarData(now(), now().plusDays(7)))
            .idAutor(gerarLong())
            .idCategoria(gerarLong());
    }
}
