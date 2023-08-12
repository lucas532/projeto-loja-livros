package com.lucas.projetolojalivros.domain.service.pais;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_PAIS_NAO_ENCONTRADO;

import com.lucas.projetolojalivros.application.port.driven.pais.PaisRepository;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.model.pais.Pais;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarPaisServiceImpl implements BuscarPaisService {

    private final PaisRepository paisRepository;

    @Override
    public Pais buscar(Long paisId) {
        return paisRepository.buscarPorId(paisId)
            .orElseThrow(
                () -> new BusinessException(ERRO_PAIS_NAO_ENCONTRADO, paisId.toString()));
    }
}
