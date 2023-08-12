package com.lucas.projetolojalivros.application.dto.output.estado;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarEstadoOutput {

    Long id;
}
