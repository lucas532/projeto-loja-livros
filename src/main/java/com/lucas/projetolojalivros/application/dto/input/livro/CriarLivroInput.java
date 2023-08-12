package com.lucas.projetolojalivros.application.dto.input.livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarLivroInput {

    @NotBlank
    String titulo;

    @NotBlank
    @Size(max = 500)
    String resumo;

    String sumario;

    @NotNull
    @DecimalMin("20.00")
    BigDecimal preco;

    @NotNull
    @Min(100)
    Integer numeroPaginas;

    @NotBlank
    String isbn;

    @NotNull
    LocalDate dataPublicacao;

    @NotNull
    Long idAutor;

    @NotNull
    Long idCategoria;
}
