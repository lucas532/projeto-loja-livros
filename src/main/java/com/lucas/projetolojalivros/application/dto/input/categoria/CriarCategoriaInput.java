package com.lucas.projetolojalivros.application.dto.input.categoria;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarCategoriaInput {

    @NotBlank
    String nome;

    String descricao;
}
