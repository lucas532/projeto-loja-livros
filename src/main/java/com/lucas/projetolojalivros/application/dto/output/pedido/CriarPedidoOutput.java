package com.lucas.projetolojalivros.application.dto.output.pedido;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CriarPedidoOutput {

    Long id;
    BigDecimal totalPedido;
}
