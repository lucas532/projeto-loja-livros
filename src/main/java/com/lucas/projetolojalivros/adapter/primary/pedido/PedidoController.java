package com.lucas.projetolojalivros.adapter.primary.pedido;

import com.lucas.projetolojalivros.application.dto.input.pedido.CriarPedidoInput;
import com.lucas.projetolojalivros.application.dto.output.pedido.CriarPedidoOutput;
import com.lucas.projetolojalivros.application.port.driver.pedido.CriarPedidoUseCase;
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
@Tag(name = "Pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;

    @PostMapping("/v1/pedidos")
    @Operation(summary = "Recurso responsável por criar um novo pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Retorna pedido criado",
            headers = @Header(name = "location", description = "Header com url do pedido criado")),
        @ApiResponse(responseCode = "422", description = "Erro de solicitação ao recurso", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Erro interno do recurso", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<CriarPedidoOutput> criar(
        @Valid @RequestBody CriarPedidoInput pedido) {
        var pedidoCriado = criarPedidoUseCase.criar(pedido);

        return ResponseEntity.created(
                URI.create(String.format("/v1/pedidos/%d", pedidoCriado.getId())))
            .body(pedidoCriado);
    }
}
