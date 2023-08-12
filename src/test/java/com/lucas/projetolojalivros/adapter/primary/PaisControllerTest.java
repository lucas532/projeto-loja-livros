package com.lucas.projetolojalivros.adapter.primary;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucas.projetolojalivros.application.dto.CriarPaisInputFixture;
import com.lucas.projetolojalivros.helper.BaseIntegrationTest;
import com.lucas.projetolojalivros.helper.annotations.pais.RemoverPaisesSql;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class PaisControllerTest extends BaseIntegrationTest {

    @Test
    @DisplayName("Deve criar um país com sucesso")
    @SneakyThrows
    @RemoverPaisesSql
    void deveCriarPaisComSucesso() {
        var paisAtual = CriarPaisInputFixture.builder().build();

        mvc.perform(MockMvcRequestBuilders.post("/v1/paises")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(paisAtual)))
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, is("/v1/paises/1")));
    }
}
