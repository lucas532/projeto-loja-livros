package com.lucas.projetolojalivros.adapter.primary.categoria;

import com.lucas.projetolojalivros.application.dto.input.categoria.CriarCategoriaInput;
import com.lucas.projetolojalivros.application.dto.output.categoria.CriarCategoriaOutput;
import com.lucas.projetolojalivros.application.port.driver.categoria.CriarCategoriaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Categorias")
public class CategoriaController {

    private final CriarCategoriaUseCase criarCategoriaUseCase;

    @PostMapping("/v1/categorias")
    @Operation(summary = "Recurso responsável por criar uma nova categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Retorna categoria criada",
            headers = @Header(name = "location", description = "Header com url da categoria criada")),
        @ApiResponse(responseCode = "422", description = "Erro de solicitação ao recurso", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Erro interno do recurso", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<CriarCategoriaOutput> criar(
        @Valid @RequestBody CriarCategoriaInput categoria) {
        var categoriaCriada = criarCategoriaUseCase.criar(categoria);

        return ResponseEntity.created(
                URI.create(String.format("/v1/categorias/%d", categoriaCriada.getId())))
            .body(categoriaCriada);
    }
}
