package com.lucas.projetolojalivros.application.dto.input.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarAutorInput {

    @NotBlank
    String nome;

    @NotBlank
    @Email
    String email;

    @Size(max = 300)
    String descricao;
}
