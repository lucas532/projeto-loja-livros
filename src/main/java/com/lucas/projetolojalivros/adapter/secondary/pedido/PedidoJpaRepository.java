package com.lucas.projetolojalivros.adapter.secondary.pedido;

import com.lucas.projetolojalivros.domain.model.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PedidoJpaRepository extends JpaRepository<Pedido, Long> {

}
