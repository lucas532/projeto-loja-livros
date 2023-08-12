package com.lucas.projetolojalivros.application.usecase.estado;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.dto.CriarEstadoInputFixture;
import com.lucas.projetolojalivros.application.port.driven.estado.EstadoRepository;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.model.estado.Estado;
import com.lucas.projetolojalivros.domain.model.pais.PaisFixture;
import com.lucas.projetolojalivros.domain.service.pais.BuscarPaisService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CriarEstadoUseCaseTest {

    @Spy
    EstadoMapperImpl estadoMapper;

    @Mock
    EstadoRepository estadoRepository;

    @Mock
    BuscarPaisService buscarPaisService;

    @InjectMocks
    CriarEstadoUseCaseImpl criarEstadoUseCase;

    @Test
    @DisplayName("Deve criar um estado com sucesso")
    void deveCriarEstadoComSucesso() {
        var captor = ArgumentCaptor.forClass(Estado.class);
        var criarEstadoInput = CriarEstadoInputFixture.builder().build();
        var paisEsperado = PaisFixture.build();

        doReturn(paisEsperado)
            .when(buscarPaisService)
            .buscar(criarEstadoInput.getIdPais());

        criarEstadoUseCase.criar(criarEstadoInput);

        verify(buscarPaisService).buscar(criarEstadoInput.getIdPais());
        verify(estadoMapper).toEstado(criarEstadoInput, paisEsperado);
        verify(estadoRepository).criar(captor.capture());

        var estadoAtual = captor.getValue();

        verify(estadoMapper).toCriarEstadoOutput(estadoAtual);

        assertThat(estadoAtual)
            .usingRecursiveComparison()
            .ignoringFields("id", "pais", "domainEvents")
            .isEqualTo(criarEstadoInput);
    }

    @Test
    @DisplayName("Deve falhar ao tentar criar um estado com nome já existente")
    void deveFalharAoTentarCriarEstadoComNomeExistente() {
        var criarEstadoInput = CriarEstadoInputFixture.builder().build();

        doReturn(true)
            .when(estadoRepository)
            .existePorNome(criarEstadoInput.getNome());

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> criarEstadoUseCase.criar(criarEstadoInput))
            .withMessage("Estado já existe: " + criarEstadoInput.getNome());

        verify(estadoRepository).existePorNome(criarEstadoInput.getNome());
    }
}
