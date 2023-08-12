package com.lucas.projetolojalivros.application.usecase.livro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.port.driven.livro.LivroRepository;
import com.lucas.projetolojalivros.domain.model.autor.Autor;
import com.lucas.projetolojalivros.domain.model.livro.LivroFixture;
import com.lucas.projetolojalivros.helper.RandomHelper;
import java.util.Optional;
import java.util.function.BiPredicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscarLivroUseCaseTest {

    @Spy
    LivroMapperImpl livroMapper;

    @Mock
    LivroRepository livroRepository;

    @InjectMocks
    BuscarLivroUseCaseImpl buscarLivroUseCase;

    @Test
    @DisplayName("Deve buscar o livro com sucesso")
    void deveBuscarLivroComSucesso() {
        var livroId = RandomHelper.gerarLong();
        var livroEsperado = LivroFixture.build();

        doReturn(Optional.of(livroEsperado))
            .when(livroRepository)
            .buscarPorId(livroId);

        var optionalLivro = buscarLivroUseCase.buscar(livroId);

        verify(livroRepository).buscarPorId(livroId);
        verify(livroMapper).toBuscarLivroOutput(livroEsperado);

        assertThat(optionalLivro).isPresent();

        var livro = optionalLivro.get();

        BiPredicate<String, Autor> mesmoAutor = (nome, autor) -> nome.equals(autor.getNome());

        assertThat(livro)
            .usingRecursiveComparison()
            .withEqualsForFields(mesmoAutor, "autor")
            .isEqualTo(livroEsperado);
    }
}
