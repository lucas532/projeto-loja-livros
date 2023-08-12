package com.lucas.projetolojalivros.adapter.primary;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucas.projetolojalivros.application.dto.CriarAutorInputFixture;
import com.lucas.projetolojalivros.helper.BaseIntegrationTest;
import com.lucas.projetolojalivros.helper.annotations.autor.RemoverAutoresSql;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class AutorControllerTest extends BaseIntegrationTest {

    @Test
    @DisplayName("Deve criar um autor com sucesso")
    @SneakyThrows
    @RemoverAutoresSql
    void deveCriarAutorComSucesso() {
        var autorAtual = CriarAutorInputFixture.builder().build();

        mvc.perform(MockMvcRequestBuilders.post("/v1/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(autorAtual)))
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, is("/v1/autores/1")));
    }
}
