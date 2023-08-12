package com.lucas.projetolojalivros.domain.service.estado;

import com.lucas.projetolojalivros.application.port.driven.estado.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarEstadoServiceImpl implements BuscarEstadoService {

    private final EstadoRepository estadoRepository;

    @Override
    public boolean existePorPais(Long idPais) {
        return estadoRepository.existePorPais(idPais);
    }
}
