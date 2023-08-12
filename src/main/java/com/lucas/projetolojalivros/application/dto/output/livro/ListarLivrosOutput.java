package com.lucas.projetolojalivros.application.dto.output.livro;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class ListarLivrosOutput {

    Long id;
    String titulo;
    String categoria;
    String autor;
}
