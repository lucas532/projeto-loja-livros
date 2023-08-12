package com.lucas.projetolojalivros.application.usecase.livro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.port.driven.livro.LivroRepository;
import com.lucas.projetolojalivros.domain.model.autor.Autor;
import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import com.lucas.projetolojalivros.domain.model.livro.Livro;
import com.lucas.projetolojalivros.domain.model.livro.LivroFixture;
import java.util.List;
import java.util.function.BiPredicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class ListarLivrosUseCaseTest {

    @Spy
    LivroMapperImpl livroMapper;

    @Mock
    LivroRepository livroRepository;

    @InjectMocks
    ListarLivrosUseCaseImpl listarLivrosUseCase;

    @Test
    @DisplayName("Deve listar os livros com sucesso")
    void deveListarLivrosComSucesso() {
        var livrosEsperados = List.of(LivroFixture.build(), LivroFixture.build());
        var pageRequest = PageRequest.of(0, 20);

        doReturn(new PageImpl<>(livrosEsperados))
            .when(livroRepository)
            .listar(pageRequest);

        var pageLivros = listarLivrosUseCase.listar(pageRequest);

        verify(livroRepository).listar(pageRequest);
        verify(livroMapper, times(livrosEsperados.size())).toListarLivrosOutput(isA(Livro.class));

        BiPredicate<String, Autor> mesmoAutor = (nome, autor) -> nome.equals(autor.getNome());
        BiPredicate<String, Categoria> mesmaCategoria = (nome, categoria) -> nome.equals(
            categoria.getNome());

        assertThat(pageLivros.getTotalElements()).isEqualTo(livrosEsperados.size());
        assertThat(pageLivros.getContent())
            .usingRecursiveComparison()
            .withEqualsForFields(mesmoAutor, "autor")
            .withEqualsForFields(mesmaCategoria, "categoria")
            .isEqualTo(livrosEsperados);
    }
}
