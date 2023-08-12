package com.lucas.projetolojalivros.adapter.primary.livro;

import com.lucas.projetolojalivros.application.dto.input.livro.CriarLivroInput;
import com.lucas.projetolojalivros.application.dto.output.livro.BuscarLivroOutput;
import com.lucas.projetolojalivros.application.dto.output.livro.CriarLivroOutput;
import com.lucas.projetolojalivros.application.dto.output.livro.ListarLivrosOutput;
import com.lucas.projetolojalivros.application.port.driver.livro.BuscarLivroUseCase;
import com.lucas.projetolojalivros.application.port.driver.livro.CriarLivroUseCase;
import com.lucas.projetolojalivros.application.port.driver.livro.ListarLivrosUseCase;
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
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Livros")
public class LivroController {

    private final CriarLivroUseCase criarLivroUseCase;
    private final ListarLivrosUseCase listarLivrosUseCase;
    private final BuscarLivroUseCase buscarLivroUseCase;

    @PostMapping("/v1/livros")
    @Operation(summary = "Recurso responsável por criar um novo livro")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Retorna livro criado",
            headers = @Header(name = "location", description = "Header com url do livro criado")),
        @ApiResponse(responseCode = "422", description = "Erro de solicitação ao recurso", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Erro interno do recurso", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<CriarLivroOutput> criar(@Valid @RequestBody CriarLivroInput livro) {
        var livroCriado = criarLivroUseCase.criar(livro);

        return ResponseEntity.created(
                URI.create(String.format("/v1/livros/%d", livroCriado.getId())))
            .body(livroCriado);
    }

    @GetMapping("/v1/livros")
    @Operation(summary = "Recurso responsável por listar livros")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna livros cadastrados"),
        @ApiResponse(responseCode = "422", description = "Erro de solicitação ao recurso", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Erro interno do recurso", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Page<ListarLivrosOutput>> listar(@ParameterObject Pageable paginacao) {
        var listaLivros = listarLivrosUseCase.listar(paginacao);

        return ResponseEntity.ok()
            .body(listaLivros);
    }

    @GetMapping("/v1/livros/{id}")
    @Operation(summary = "Recurso responsável por buscar livro")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna detalhes do livro"),
        @ApiResponse(responseCode = "204", description = "Sem conteúdo", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", description = "Erro interno do recurso", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<BuscarLivroOutput> buscar(@PathVariable Long id) {
        var optionalLivro = buscarLivroUseCase.buscar(id);

        return optionalLivro.map(livro -> ResponseEntity.ok().body(livro))
            .orElse(ResponseEntity.noContent().build());
    }
}
