package com.lucas.projetolojalivros.adapter.primary.estado;

import com.lucas.projetolojalivros.application.dto.input.estado.CriarEstadoInput;
import com.lucas.projetolojalivros.application.dto.output.estado.CriarEstadoOutput;
import com.lucas.projetolojalivros.application.port.driver.estado.CriarEstadoUseCase;
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
@Tag(name = "Estados")
public class EstadoController {

    private final CriarEstadoUseCase criarEstadoUseCase;

    @PostMapping("/v1/estados")
    @Operation(summary = "Recurso responsável por criar um novo estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Retorna estado criado",
            headers = @Header(name = "location", description = "Header com url do estado criado")),
        @ApiResponse(responseCode = "422", description = "Erro de solicitação ao recurso", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Erro interno do recurso", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<CriarEstadoOutput> criar(
        @Valid @RequestBody CriarEstadoInput estado) {
        var estadoCriado = criarEstadoUseCase.criar(estado);

        return ResponseEntity.created(
                URI.create(String.format("/v1/estados/%d", estadoCriado.getId())))
            .body(estadoCriado);
    }
}
