package com.lucas.projetolojalivros.adapter.primary.pais;

import com.lucas.projetolojalivros.application.dto.input.pais.CriarPaisInput;
import com.lucas.projetolojalivros.application.dto.output.pais.CriarPaisOutput;
import com.lucas.projetolojalivros.application.port.driver.pais.CriarPaisUseCase;
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
@Tag(name = "Países")
public class PaisController {

    private final CriarPaisUseCase criarPaisUseCase;

    @PostMapping("/v1/paises")
    @Operation(summary = "Recurso responsável por criar um novo país")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Retorna país criado",
            headers = @Header(name = "location", description = "Header com url do país criado")),
        @ApiResponse(responseCode = "422", description = "Erro de solicitação ao recurso", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Erro interno do recurso", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<CriarPaisOutput> criar(
        @Valid @RequestBody CriarPaisInput pais) {
        var paisCriado = criarPaisUseCase.criar(pais);

        return ResponseEntity.created(
                URI.create(String.format("/v1/paises/%d", paisCriado.getId())))
            .body(paisCriado);
    }
}
