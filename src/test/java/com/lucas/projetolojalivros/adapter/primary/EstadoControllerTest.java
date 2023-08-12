package com.lucas.projetolojalivros.adapter.primary;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucas.projetolojalivros.application.dto.CriarEstadoInputFixture;
import com.lucas.projetolojalivros.helper.BaseIntegrationTest;
import com.lucas.projetolojalivros.helper.annotations.estado.RemoverEstadosSql;
import com.lucas.projetolojalivros.helper.annotations.pais.InserirPaisSql;
import com.lucas.projetolojalivros.helper.annotations.pais.RemoverPaisesSql;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class EstadoControllerTest extends BaseIntegrationTest {

    @Test
    @DisplayName("Deve criar um estado com sucesso")
    @SneakyThrows
    @InserirPaisSql
    @RemoverEstadosSql
    @RemoverPaisesSql
    void deveCriarPaisComSucesso() {
        var estadoAtual = CriarEstadoInputFixture.builder()
            .idPais(1L)
            .build();

        mvc.perform(MockMvcRequestBuilders.post("/v1/estados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(estadoAtual)))
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, is("/v1/estados/1")));
    }
}
