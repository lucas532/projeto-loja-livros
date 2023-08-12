package com.lucas.projetolojalivros.adapter.primary;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucas.projetolojalivros.application.dto.CriarAutorInputFixture;
import com.lucas.projetolojalivros.helper.BaseIntegrationTest;
import com.lucas.projetolojalivros.helper.annotations.autor.RemoverAutoresSql;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
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
            .andExpect(header().string(HttpHeaders.LOCATION, is("/v1/autores/1")))
            .andDo(document("v1/autores",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("nome").description("Nome do autor"),
                    fieldWithPath("email").description("Email do autor"),
                    fieldWithPath("descricao").description("Descrição do autor")
                ),
                responseHeaders(
                    headerWithName("location").description("Localização do autor criado")
                ),
                responseFields(
                    fieldWithPath("id").description("Id do autor criado")
                ))
            );
    }
}
