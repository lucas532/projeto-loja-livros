package com.lucas.projetolojalivros.application.usecase.pais;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_PAIS_JA_EXISTE;

import com.lucas.projetolojalivros.application.dto.input.pais.CriarPaisInput;
import com.lucas.projetolojalivros.application.dto.output.pais.CriarPaisOutput;
import com.lucas.projetolojalivros.application.port.driven.pais.PaisRepository;
import com.lucas.projetolojalivros.application.port.driver.pais.CriarPaisUseCase;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarPaisUseCaseImpl implements CriarPaisUseCase {

    private final PaisMapper paisMapper;
    private final PaisRepository paisRepository;

    @Override
    public CriarPaisOutput criar(CriarPaisInput paisInput) {
        if (paisRepository.existePorNome(paisInput.getNome())) {
            throw new BusinessException(ERRO_PAIS_JA_EXISTE, paisInput.getNome());
        }

        var pais = paisMapper.toPais(paisInput);

        paisRepository.criar(pais);

        return paisMapper.toCriarPaisOutput(pais);
    }
}
