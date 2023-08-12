package com.lucas.projetolojalivros.adapter.secondary.pedido;

import com.lucas.projetolojalivros.application.port.driven.pedido.PedidoRepository;
import com.lucas.projetolojalivros.domain.model.pedido.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoJpaRepository pedidoJpaRepository;

    @Override
    public Pedido criar(Pedido pedido) {
        return this.pedidoJpaRepository.save(pedido);
    }
}
