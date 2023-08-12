package com.lucas.projetolojalivros.domain.service.pais;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.port.driven.pais.PaisRepository;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.model.pais.PaisFixture;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscarPaisServiceTest {

    @Mock
    PaisRepository paisRepository;

    @InjectMocks
    BuscarPaisServiceImpl buscarPaisServiceImpl;

    @Test
    @DisplayName("Deve buscar um país com sucesso")
    void deveBuscarPaisComSucesso() {
        var paisId = gerarLong();
        var paisEsperado = PaisFixture.build();

        doReturn(Optional.of(paisEsperado))
            .when(paisRepository)
            .buscarPorId(paisId);

        var paisAtual = buscarPaisServiceImpl.buscar(paisId);

        verify(paisRepository).buscarPorId(paisId);

        assertThat(paisAtual)
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(paisEsperado);
    }

    @Test
    @DisplayName("Deve falhar ao buscar um país que não existe")
    void deveFalharAoBuscarPaisInexistente() {
        var paisId = gerarLong();

        doReturn(Optional.empty())
            .when(paisRepository)
            .buscarPorId(paisId);

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> buscarPaisServiceImpl.buscar(paisId))
            .withMessage("País não encontrado: " + paisId);

        verify(paisRepository).buscarPorId(paisId);
    }
}
