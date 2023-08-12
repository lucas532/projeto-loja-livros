package com.lucas.projetolojalivros.application.dto.output.livro;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class BuscarLivroOutput {

    String titulo;
    String autor;
    BigDecimal preco;
    String isbn;
    String resumo;
    String sumario;
}
