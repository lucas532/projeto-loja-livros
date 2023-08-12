package com.lucas.projetolojalivros.adapter.primary;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucas.projetolojalivros.application.dto.CriarPedidoInputFixture;
import com.lucas.projetolojalivros.helper.BaseIntegrationTest;
import com.lucas.projetolojalivros.helper.annotations.livro.InserirLivroSql;
import com.lucas.projetolojalivros.helper.annotations.livro.RemoverLivrosSql;
import com.lucas.projetolojalivros.helper.annotations.pedido.RemoverPedidosSql;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class PedidoControllerTest extends BaseIntegrationTest {

    @Test
    @DisplayName("Deve criar um pedido com sucesso")
    @SneakyThrows
    @InserirLivroSql
    @RemoverPedidosSql
    @RemoverLivrosSql
    void deveCriarPedidoComSucesso() {
        var pedidoAtual = CriarPedidoInputFixture.builder()
            .itens(List.of(CriarPedidoInputFixture.buildItem()
                .idLivro(1L)
                .build()))
            .build();

        mvc.perform(MockMvcRequestBuilders.post("/v1/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(pedidoAtual)))
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, is("/v1/pedidos/1")));
    }
}
