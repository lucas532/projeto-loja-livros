package com.lucas.projetolojalivros.adapter.primary;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucas.projetolojalivros.application.dto.CriarCategoriaInputFixture;
import com.lucas.projetolojalivros.helper.BaseIntegrationTest;
import com.lucas.projetolojalivros.helper.annotations.categoria.RemoverCategoriasSql;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class CategoriaControllerTest extends BaseIntegrationTest {

    @Test
    @DisplayName("Deve criar uma categoria com sucesso")
    @SneakyThrows
    @RemoverCategoriasSql
    void deveCriarCategoriaComSucesso() {
        var categoriaAtual = CriarCategoriaInputFixture.builder().build();

        mvc.perform(MockMvcRequestBuilders.post("/v1/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(categoriaAtual)))
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, is("/v1/categorias/1")));
    }
}
