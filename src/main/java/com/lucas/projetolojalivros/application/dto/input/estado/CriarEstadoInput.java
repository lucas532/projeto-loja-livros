package com.lucas.projetolojalivros.application.dto.input.estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarEstadoInput {

    @NotBlank
    String nome;

    @NotNull
    Long idPais;
}
