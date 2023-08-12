package com.lucas.projetolojalivros.domain.model.pedido;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_LIVRO_NAO_ENCONTRADO;

import com.lucas.projetolojalivros.application.dto.input.pedido.CriarPedidoInput;
import com.lucas.projetolojalivros.application.port.driven.livro.LivroRepository;
import com.lucas.projetolojalivros.application.usecase.pedido.ItemMapper;
import com.lucas.projetolojalivros.application.usecase.pedido.PedidoMapper;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoFactory {

    private final PedidoMapper pedidoMapper;
    private final ItemMapper itemMapper;
    private final LivroRepository livroRepository;

    public Pedido criar(CriarPedidoInput pedidoInput) {
        var itens = new ArrayList<Item>();

        pedidoInput.getItens().forEach(item -> {
            var livro = livroRepository.buscarPorId(item.getIdLivro())
                .orElseThrow(() -> new BusinessException(ERRO_LIVRO_NAO_ENCONTRADO,
                    item.getIdLivro().toString()));

            itens.add(itemMapper.toItem(item, livro));
        });

        var pedido = pedidoMapper.toPedido(pedidoInput, itens);
        pedido.getItens().forEach(item -> item.setPedido(pedido));

        return pedido;
    }
}
