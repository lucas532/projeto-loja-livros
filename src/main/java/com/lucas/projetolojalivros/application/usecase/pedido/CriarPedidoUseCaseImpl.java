package com.lucas.projetolojalivros.application.usecase.pedido;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_CRIAR_PEDIDO;

import com.lucas.projetolojalivros.application.dto.input.pedido.CriarPedidoInput;
import com.lucas.projetolojalivros.application.dto.output.pedido.CriarPedidoOutput;
import com.lucas.projetolojalivros.application.port.driven.pedido.PedidoRepository;
import com.lucas.projetolojalivros.application.port.driver.pedido.CriarPedidoUseCase;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.model.pedido.PedidoFactory;
import com.lucas.projetolojalivros.domain.service.estado.BuscarEstadoService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    private final PedidoRepository pedidoRepository;
    private final PedidoFactory pedidoFactory;
    private final PedidoMapper pedidoMapper;
    private final BuscarEstadoService buscarEstadoService;

    @Override
    public CriarPedidoOutput criar(CriarPedidoInput pedidoInput) {
        if (buscarEstadoService.existePorPais(pedidoInput.getIdPais())) {
            throw new BusinessException(ERRO_CRIAR_PEDIDO,
                "País informado possui estados, portanto estado deve ser informado");
        }

        var pedido = pedidoFactory.criar(pedidoInput);

        pedidoRepository.criar(pedido);

        var totalPedido = pedido.getItens().stream()
            .map(item -> item.getLivro().getPreco())
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return pedidoMapper.toCriarPedidoOutput(pedido, totalPedido);
    }
}
