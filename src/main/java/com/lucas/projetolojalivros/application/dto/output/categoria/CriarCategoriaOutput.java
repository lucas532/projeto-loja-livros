package com.lucas.projetolojalivros.application.dto.output.categoria;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarCategoriaOutput {

    Long id;
}
