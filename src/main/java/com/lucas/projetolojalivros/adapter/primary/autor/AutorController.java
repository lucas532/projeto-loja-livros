package com.lucas.projetolojalivros.adapter.primary.autor;

import com.lucas.projetolojalivros.application.dto.input.autor.CriarAutorInput;
import com.lucas.projetolojalivros.application.dto.output.autor.CriarAutorOutput;
import com.lucas.projetolojalivros.application.port.driver.autor.CriarAutorUseCase;
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
@Tag(name = "Autores")
public class AutorController {

    private final CriarAutorUseCase criarAutorUseCase;

    @PostMapping("/v1/autores")
    @Operation(summary = "Recurso responsável por criar um novo autor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Retorna autor criado",
            headers = @Header(name = "location", description = "Header com url do autor criado")),
        @ApiResponse(responseCode = "422", description = "Erro de solicitação ao recurso", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Erro interno do recurso", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<CriarAutorOutput> criar(@Valid @RequestBody CriarAutorInput autor) {
        var autorCriado = criarAutorUseCase.criar(autor);

        return ResponseEntity.created(
                URI.create(String.format("/v1/autores/%d", autorCriado.getId())))
            .body(autorCriado);
    }
}
