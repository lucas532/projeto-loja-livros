package com.lucas.projetolojalivros.domain.service.estado;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.port.driven.estado.EstadoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscarEstadoServiceTest {

    @Mock
    EstadoRepository estadoRepository;

    @InjectMocks
    BuscarEstadoServiceImpl buscarEstadoServiceImpl;

    @Test
    @DisplayName("Deve buscar um estado a partir do país com sucesso")
    void deveBuscarEstadoPorPaisComSucesso() {
        var idPais = gerarLong();

        doReturn(true)
            .when(estadoRepository)
            .existePorPais(idPais);

        var estadoExiste = buscarEstadoServiceImpl.existePorPais(idPais);

        assertThat(estadoExiste).isTrue();

        verify(estadoRepository).existePorPais(idPais);
    }

    @Test
    @DisplayName("Deve falhar ao buscar um estado com país inexistente")
    void deveFalharAoBuscarEstadoPorPaisInexistente() {
        var idPais = gerarLong();

        doReturn(false)
            .when(estadoRepository)
            .existePorPais(idPais);

        var estadoExiste = buscarEstadoServiceImpl.existePorPais(idPais);

        assertThat(estadoExiste).isFalse();

        verify(estadoRepository).existePorPais(idPais);
    }
}
