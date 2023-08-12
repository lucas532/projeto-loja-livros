package com.lucas.projetolojalivros.application.dto.input.pedido;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarPedidoInput {

    @NotBlank
    String email;

    @NotBlank
    String nome;

    @NotBlank
    String sobrenome;

    @NotBlank
    @Pattern(regexp = "([\\d]{11})|([\\d]{14})")
    String cpfCnpj;

    @NotBlank
    String endereco;

    @NotBlank
    String complemento;

    @NotBlank
    String cidade;

    @NotBlank
    String telefone;

    @NotBlank
    String cep;

    @NotNull
    Long idPais;

    Long idEstado;

    @NotEmpty
    Collection<ItemPedidoDto> itens;
}
