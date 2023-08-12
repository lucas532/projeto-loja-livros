package com.lucas.projetolojalivros.application.usecase.livro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.dto.CriarLivroInputFixture;
import com.lucas.projetolojalivros.application.port.driven.livro.LivroRepository;
import com.lucas.projetolojalivros.domain.model.autor.AutorFixture;
import com.lucas.projetolojalivros.domain.model.categoria.CategoriaFixture;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.model.livro.Livro;
import com.lucas.projetolojalivros.domain.service.autor.BuscarAutorService;
import com.lucas.projetolojalivros.domain.service.categoria.BuscarCategoriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CriarLivroUseCaseTest {

    @Spy
    LivroMapperImpl livroMapper;

    @Mock
    LivroRepository livroRepository;

    @Mock
    BuscarAutorService buscarAutorService;

    @Mock
    BuscarCategoriaService buscarCategoriaService;

    @InjectMocks
    CriarLivroUseCaseImpl criarLivroUseCase;

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    void deveCriarLivroComSucesso() {
        var captor = ArgumentCaptor.forClass(Livro.class);
        var criarLivroInput = CriarLivroInputFixture.builder().build();
        var autorEsperado = AutorFixture.build();
        var categoriaEsperada = CategoriaFixture.build();

        doReturn(autorEsperado)
            .when(buscarAutorService)
            .buscar(criarLivroInput.getIdAutor());

        doReturn(categoriaEsperada)
            .when(buscarCategoriaService)
            .buscar(criarLivroInput.getIdCategoria());

        criarLivroUseCase.criar(criarLivroInput);

        verify(livroRepository).existePorTitulo(criarLivroInput.getTitulo());
        verify(livroRepository).existePorIsbn(criarLivroInput.getIsbn());
        verify(buscarAutorService).buscar(criarLivroInput.getIdAutor());
        verify(buscarCategoriaService).buscar(criarLivroInput.getIdCategoria());
        verify(livroMapper).toLivro(criarLivroInput, autorEsperado, categoriaEsperada);
        verify(livroRepository).criar(captor.capture());

        var livroAtual = captor.getValue();

        verify(livroMapper).toCriarLivroOutput(livroAtual);

        assertThat(livroAtual)
            .usingRecursiveComparison()
            .ignoringFields("id", "autor", "categoria", "domainEvents")
            .isEqualTo(criarLivroInput);
    }

    @Test
    @DisplayName("Deve falhar ao tentar criar um livro com título já existente")
    void deveFalharAoTentarCriarLivroComTituloExistente() {
        var criarLivroInput = CriarLivroInputFixture.builder().build();

        doReturn(true)
            .when(livroRepository)
            .existePorTitulo(criarLivroInput.getTitulo());

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> criarLivroUseCase.criar(criarLivroInput))
            .withMessage("Erro ao criar livro: Título já cadastrado");

        verify(livroRepository).existePorTitulo(criarLivroInput.getTitulo());
    }

    @Test
    @DisplayName("Deve falhar ao tentar criar um livro com Isbn já existente")
    void deveFalharAoTentarCriarLivroComIsbnExistente() {
        var criarLivroInput = CriarLivroInputFixture.builder().build();

        doReturn(false)
            .when(livroRepository)
            .existePorTitulo(criarLivroInput.getTitulo());

        doReturn(true)
            .when(livroRepository)
            .existePorIsbn(criarLivroInput.getIsbn());

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> criarLivroUseCase.criar(criarLivroInput))
            .withMessage("Erro ao criar livro: Isbn já cadastrado");

        verify(livroRepository).existePorTitulo(criarLivroInput.getTitulo());
        verify(livroRepository).existePorIsbn(criarLivroInput.getIsbn());
    }
}
