package com.lucas.projetolojalivros.application.usecase.autor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.lucas.projetolojalivros.application.dto.CriarAutorInputFixture;
import com.lucas.projetolojalivros.application.port.driven.autor.AutorRepository;
import com.lucas.projetolojalivros.domain.model.autor.Autor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CriarAutorUseCaseTest {

    @Spy
    AutorMapperImpl autorMapper;

    @Mock
    AutorRepository autorRepository;

    @InjectMocks
    CriarAutorUseCaseImpl criarAutorUseCaseImpl;

    @Test
    @DisplayName("Deve criar um autor com sucesso")
    void deveCriarAutorComSucesso() {
        var captor = ArgumentCaptor.forClass(Autor.class);
        var criarAutorInput = CriarAutorInputFixture.builder().build();

        criarAutorUseCaseImpl.criar(criarAutorInput);

        verify(autorMapper).toAutor(criarAutorInput);
        verify(autorRepository).criar(captor.capture());

        var autorAtual = captor.getValue();

        verify(autorMapper).toCriarAutorOutput(autorAtual);

        assertThat(autorAtual)
            .usingRecursiveComparison()
            .ignoringFields("id", "criadoEm", "domainEvents")
            .isEqualTo(criarAutorInput);
    }
}
