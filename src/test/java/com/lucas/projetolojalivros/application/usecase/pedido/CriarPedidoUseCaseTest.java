package com.lucas.projetolojalivros.application.usecase.pedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.dto.CriarPedidoInputFixture;
import com.lucas.projetolojalivros.application.port.driven.pedido.PedidoRepository;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.model.pedido.Pedido;
import com.lucas.projetolojalivros.domain.model.pedido.PedidoFactory;
import com.lucas.projetolojalivros.domain.model.pedido.PedidoFixture;
import com.lucas.projetolojalivros.domain.service.estado.BuscarEstadoService;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CriarPedidoUseCaseTest {

    @Spy
    PedidoMapperImpl pedidoMapper;

    @Mock
    PedidoFactory pedidoFactory;

    @Mock
    PedidoRepository pedidoRepository;

    @Mock
    BuscarEstadoService buscarEstadoService;

    @InjectMocks
    CriarPedidoUseCaseImpl criarPedidoUseCase;

    @Test
    @DisplayName("Deve criar um pedido com sucesso")
    void deveCriarPedidoComSucesso() {
        var captor = ArgumentCaptor.forClass(Pedido.class);
        var criarPedidoInput = CriarPedidoInputFixture.builder().build();
        var idPais = criarPedidoInput.getIdPais();
        var totalEsperado = new BigDecimal(50);
        var pedidoEsperado = PedidoFixture.buildComCpfEValorTotal(criarPedidoInput.getCpfCnpj(),
            totalEsperado);

        doReturn(false)
            .when(buscarEstadoService)
            .existePorPais(idPais);

        doReturn(pedidoEsperado)
            .when(pedidoFactory)
            .criar(criarPedidoInput);

        criarPedidoUseCase.criar(criarPedidoInput);

        verify(buscarEstadoService).existePorPais(idPais);
        verify(pedidoFactory).criar(criarPedidoInput);
        verify(pedidoRepository).criar(captor.capture());

        var pedidoAtual = captor.getValue();

        verify(pedidoMapper).toCriarPedidoOutput(pedidoAtual, totalEsperado);

        assertThat(pedidoAtual)
            .usingRecursiveComparison()
            .isEqualTo(pedidoEsperado);
    }

    @Test
    @DisplayName("Deve falhar ao tentar criar um pedido com país sem estado informado")
    void deveFalharAoTentarCriarPedidoComPaisSemEstadoInformado() {
        var criarPedidoInput = CriarPedidoInputFixture.builder().build();
        var idPais = criarPedidoInput.getIdPais();

        doReturn(true)
            .when(buscarEstadoService)
            .existePorPais(idPais);

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> criarPedidoUseCase.criar(criarPedidoInput))
            .withMessage(
                "Erro ao criar pedido: País informado possui estados, portanto estado deve ser informado");

        verify(buscarEstadoService).existePorPais(idPais);
    }
}
