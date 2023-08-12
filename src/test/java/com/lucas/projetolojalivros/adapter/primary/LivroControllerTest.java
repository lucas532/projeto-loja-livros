package com.lucas.projetolojalivros.adapter.primary;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucas.projetolojalivros.application.dto.CriarLivroInputFixture;
import com.lucas.projetolojalivros.helper.BaseIntegrationTest;
import com.lucas.projetolojalivros.helper.annotations.autor.InserirAutorSql;
import com.lucas.projetolojalivros.helper.annotations.categoria.InserirCategoriaSql;
import com.lucas.projetolojalivros.helper.annotations.livro.InserirLivroSql;
import com.lucas.projetolojalivros.helper.annotations.livro.RemoverLivrosSql;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class LivroControllerTest extends BaseIntegrationTest {

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    @SneakyThrows
    @InserirAutorSql
    @InserirCategoriaSql
    @RemoverLivrosSql
    void deveCriarLivroComSucesso() {
        var livroAtual = CriarLivroInputFixture.builder().idAutor(1L).idCategoria(1L).build();

        mvc.perform(MockMvcRequestBuilders.post("/v1/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(livroAtual)))
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, is("/v1/livros/1")));
    }

    @Test
    @DisplayName("Deve listar os livros com sucesso")
    @SneakyThrows
    @InserirLivroSql
    @RemoverLivrosSql
    void deveListarLivrosComSucesso() {
        mvc.perform(MockMvcRequestBuilders.get("/v1/livros")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("totalPages", equalTo(1)))
            .andExpect(jsonPath("totalElements", equalTo(1)));
    }

    @Test
    @DisplayName("Deve buscar o livro com sucesso")
    @SneakyThrows
    @InserirLivroSql
    @RemoverLivrosSql
    void deveBuscarLivroComSucesso() {
        mvc.perform(MockMvcRequestBuilders.get("/v1/livros/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
