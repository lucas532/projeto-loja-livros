package com.lucas.projetolojalivros.application.usecase.pais;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.dto.CriarPaisInputFixture;
import com.lucas.projetolojalivros.application.port.driven.pais.PaisRepository;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.model.pais.Pais;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CriarPaisUseCaseTest {

    @Spy
    PaisMapperImpl paisMapper;

    @Mock
    PaisRepository paisRepository;

    @InjectMocks
    CriarPaisUseCaseImpl criarPaisUseCase;

    @Test
    @DisplayName("Deve criar um país com sucesso")
    void deveCriarPaisComSucesso() {
        var captor = ArgumentCaptor.forClass(Pais.class);
        var criarPaisInput = CriarPaisInputFixture.builder().build();

        criarPaisUseCase.criar(criarPaisInput);

        verify(paisRepository).existePorNome(criarPaisInput.getNome());
        verify(paisMapper).toPais(criarPaisInput);
        verify(paisRepository).criar(captor.capture());

        var paisAtual = captor.getValue();

        verify(paisMapper).toCriarPaisOutput(paisAtual);

        assertThat(paisAtual)
            .usingRecursiveComparison()
            .ignoringFields("id", "domainEvents")
            .isEqualTo(criarPaisInput);
    }

    @Test
    @DisplayName("Deve falhar ao tentar criar um país com nome já existente")
    void deveFalharAoTentarCriarPaisComNomeExistente() {
        var criarPaisInput = CriarPaisInputFixture.builder().build();

        doReturn(true)
            .when(paisRepository)
            .existePorNome(criarPaisInput.getNome());

        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> criarPaisUseCase.criar(criarPaisInput))
            .withMessage("País já existe: " + criarPaisInput.getNome());

        verify(paisRepository).existePorNome(criarPaisInput.getNome());
    }
}
