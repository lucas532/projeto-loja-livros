package com.lucas.projetolojalivros.application.dto.output.livro;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarLivroOutput {

    Long id;
}
