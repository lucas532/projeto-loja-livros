package com.lucas.projetolojalivros.domain.service.categoria;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.port.driven.categoria.CategoriaRepository;
import com.lucas.projetolojalivros.domain.model.categoria.CategoriaFixture;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscarCategoriaServiceTest {

    @Mock
    CategoriaRepository categoriaRepository;

    @InjectMocks
    BuscarCategoriaServiceImpl buscarCategoriaServiceImpl;

    @Test
    @DisplayName("Deve buscar uma categoria com sucesso")
    void deveBuscarCategoriaComSucesso() {
        var categoriaId = gerarLong();
        var categoriaEsperada = CategoriaFixture.build();

        doReturn(Optional.of(categoriaEsperada))
            .when(categoriaRepository)
            .buscarPorId(categoriaId);

        var categoriaAtual = buscarCategoriaServiceImpl.buscar(categoriaId);

        verify(categoriaRepository).buscarPorId(categoriaId);

        assertThat(categoriaAtual)
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(categoriaEsperada);
    }

    @Test
    @DisplayName("Deve falhar ao buscar uma categoria que não existe")
    void deveFalharAoBuscarCategoriaInexistente() {
        var categoriaId = gerarLong();

        doReturn(Optional.empty())
            .when(categoriaRepository)
            .buscarPorId(categoriaId);

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> buscarCategoriaServiceImpl.buscar(categoriaId))
            .withMessage("Categoria não encontrada: " + categoriaId);

        verify(categoriaRepository).buscarPorId(categoriaId);
    }
}
