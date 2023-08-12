package com.lucas.projetolojalivros.application.port.driver.pedido;

import com.lucas.projetolojalivros.application.dto.input.pedido.CriarPedidoInput;
import com.lucas.projetolojalivros.application.dto.output.pedido.CriarPedidoOutput;

public interface CriarPedidoUseCase {

    CriarPedidoOutput criar(CriarPedidoInput pedidoInput);
}
