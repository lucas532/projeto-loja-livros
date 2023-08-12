package com.lucas.projetolojalivros.domain.service.autor;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.port.driven.autor.AutorRepository;
import com.lucas.projetolojalivros.domain.model.autor.AutorFixture;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscarAutorServiceTest {

    @Mock
    AutorRepository autorRepository;

    @InjectMocks
    BuscarAutorServiceImpl buscarAutorServiceImpl;

    @Test
    @DisplayName("Deve buscar um autor com sucesso")
    void deveBuscarAutorComSucesso() {
        var autorId = gerarLong();
        var autorEsperado = AutorFixture.build();

        doReturn(Optional.of(autorEsperado))
            .when(autorRepository)
            .buscarPorId(autorId);

        var autorAtual = buscarAutorServiceImpl.buscar(autorId);

        verify(autorRepository).buscarPorId(autorId);

        assertThat(autorAtual)
            .usingRecursiveComparison()
            .ignoringFields("id", "criadoEm")
            .isEqualTo(autorEsperado);
    }

    @Test
    @DisplayName("Deve falhar ao buscar um autor que não existe")
    void deveFalharAoBuscarAutorInexistente() {
        var autorId = gerarLong();

        doReturn(Optional.empty())
            .when(autorRepository)
            .buscarPorId(autorId);

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> buscarAutorServiceImpl.buscar(autorId))
            .withMessage("Autor não encontrado: " + autorId);

        verify(autorRepository).buscarPorId(autorId);
    }
}
