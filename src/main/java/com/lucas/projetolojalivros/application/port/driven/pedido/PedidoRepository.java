package com.lucas.projetolojalivros.application.port.driven.pedido;

import com.lucas.projetolojalivros.domain.model.pedido.Pedido;

public interface PedidoRepository {

    Pedido criar(Pedido pedido);
}
