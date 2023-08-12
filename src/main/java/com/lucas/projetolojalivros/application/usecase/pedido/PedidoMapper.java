package com.lucas.projetolojalivros.application.usecase.pedido;

import com.lucas.projetolojalivros.application.dto.input.pedido.CriarPedidoInput;
import com.lucas.projetolojalivros.application.dto.output.pedido.CriarPedidoOutput;
import com.lucas.projetolojalivros.domain.model.pedido.Item;
import com.lucas.projetolojalivros.domain.model.pedido.Pedido;
import java.math.BigDecimal;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(target = "itens", source = "itens")
    Pedido toPedido(CriarPedidoInput criarLivroInput, Collection<Item> itens);

    CriarPedidoOutput toCriarPedidoOutput(Pedido pedido, BigDecimal totalPedido);
}
