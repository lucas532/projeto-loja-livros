package com.lucas.projetolojalivros.application.dto.output.autor;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarAutorOutput {

    Long id;
}
