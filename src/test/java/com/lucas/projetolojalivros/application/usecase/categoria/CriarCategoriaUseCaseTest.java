package com.lucas.projetolojalivros.application.usecase.categoria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.dto.CriarCategoriaInputFixture;
import com.lucas.projetolojalivros.application.port.driven.categoria.CategoriaRepository;
import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CriarCategoriaUseCaseTest {

    @Spy
    CategoriaMapperImpl categoriaMapper;

    @Mock
    CategoriaRepository categoriaRepository;

    @InjectMocks
    CriarCategoriaUseCaseImpl criarCategoriaUseCase;

    @Test
    @DisplayName("Deve criar uma categoria com sucesso")
    void deveCriarCategoriaComSucesso() {
        var captor = ArgumentCaptor.forClass(Categoria.class);
        var criarCategoriaInput = CriarCategoriaInputFixture.builder().build();

        criarCategoriaUseCase.criar(criarCategoriaInput);

        verify(categoriaMapper).toCategoria(criarCategoriaInput);
        verify(categoriaRepository).criar(captor.capture());

        var categoriaAtual = captor.getValue();

        verify(categoriaMapper).toCriarCategoriaOutput(categoriaAtual);

        assertThat(categoriaAtual)
            .usingRecursiveComparison()
            .ignoringFields("id", "domainEvents")
            .isEqualTo(criarCategoriaInput);
    }

    @Test
    @DisplayName("Deve falhar ao tentar criar uma categoria já existente")
    void deveFalharAoTentarCriarCategoriaExistente() {
        var criarCategoriaInput = CriarCategoriaInputFixture.builder().build();

        doReturn(true)
            .when(categoriaRepository)
            .existePorNome(criarCategoriaInput.getNome());

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> criarCategoriaUseCase.criar(criarCategoriaInput))
            .withMessage("Categoria já existe: " + criarCategoriaInput.getNome());

        verify(categoriaRepository).existePorNome(criarCategoriaInput.getNome());
    }
}
