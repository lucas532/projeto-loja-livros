package com.lucas.projetolojalivros.application.usecase.pedido;

import com.lucas.projetolojalivros.application.dto.input.pedido.ItemPedidoDto;
import com.lucas.projetolojalivros.domain.model.livro.Livro;
import com.lucas.projetolojalivros.domain.model.pedido.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItem(ItemPedidoDto itemPedidoDto, Livro livro);
}
