package com.lucas.projetolojalivros.application.dto.output.pais;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarPaisOutput {

    Long id;
}
