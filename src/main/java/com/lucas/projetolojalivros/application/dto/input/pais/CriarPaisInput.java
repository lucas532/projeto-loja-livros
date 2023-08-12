package com.lucas.projetolojalivros.application.dto.input.pais;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarPaisInput {

    @NotBlank
    String nome;
}
