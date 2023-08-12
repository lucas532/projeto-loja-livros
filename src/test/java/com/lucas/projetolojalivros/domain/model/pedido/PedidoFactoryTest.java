package com.lucas.projetolojalivros.domain.model.pedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.dto.CriarPedidoInputFixture;
import com.lucas.projetolojalivros.application.port.driven.livro.LivroRepository;
import com.lucas.projetolojalivros.application.usecase.pedido.ItemMapperImpl;
import com.lucas.projetolojalivros.application.usecase.pedido.PedidoMapperImpl;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.model.livro.LivroFixture;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoFactoryTest {

    @Spy
    PedidoMapperImpl pedidoMapper;

    @Spy
    ItemMapperImpl itemMapper;

    @Mock
    LivroRepository livroRepository;

    @InjectMocks
    PedidoFactory pedidoFactory;

    @Test
    @DisplayName("Deve criar um pedido com sucesso")
    void deveCriarPedidoComSucesso() {
        var criarPedidoInput = CriarPedidoInputFixture.builder().build();
        var itemPedido = criarPedidoInput.getItens().iterator().next();
        var idLivroPedido = itemPedido.getIdLivro();
        var livroEsperado = LivroFixture.build();
        var itensEsperados = List.of(
            ItemFixture.buildComQuantidadeELivro(itemPedido.getQuantidade(), livroEsperado));
        var pedidoEsperado = PedidoFixture.buildComCpfEItens(criarPedidoInput.getCpfCnpj(),
            itensEsperados);

        doReturn(Optional.of(livroEsperado))
            .when(livroRepository)
            .buscarPorId(idLivroPedido);

        var pedidoAtual = pedidoFactory.criar(criarPedidoInput);

        verify(livroRepository).buscarPorId(idLivroPedido);
        verify(itemMapper).toItem(itemPedido, livroEsperado);
        verify(pedidoMapper).toPedido(eq(criarPedidoInput), anyList());

        assertThat(pedidoAtual)
            .usingRecursiveComparison()
            .ignoringFields("itens.pedido")
            .isEqualTo(pedidoEsperado);
    }

    @Test
    @DisplayName("Deve falhar ao tentar criar um pedido com livro não existente")
    void deveFalharAoTentarCriarPedidoComLivroNaoExistente() {
        var criarPedidoInput = CriarPedidoInputFixture.builder().build();
        var itemPedido = criarPedidoInput.getItens().iterator().next();
        var idLivro = itemPedido.getIdLivro();

        doReturn(Optional.empty())
            .when(livroRepository)
            .buscarPorId(idLivro);

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> pedidoFactory.criar(criarPedidoInput))
            .withMessage("Livro não encontrado: " + idLivro);

        verify(livroRepository).buscarPorId(idLivro);
    }
}
