package com.lucas.projetolojalivros.application.usecase.estado;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_ESTADO_JA_EXISTE;

import com.lucas.projetolojalivros.application.dto.input.estado.CriarEstadoInput;
import com.lucas.projetolojalivros.application.dto.output.estado.CriarEstadoOutput;
import com.lucas.projetolojalivros.application.port.driven.estado.EstadoRepository;
import com.lucas.projetolojalivros.application.port.driver.estado.CriarEstadoUseCase;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.service.pais.BuscarPaisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarEstadoUseCaseImpl implements CriarEstadoUseCase {

    private final EstadoMapper estadoMapper;
    private final EstadoRepository estadoRepository;
    private final BuscarPaisService buscarPaisService;

    @Override
    public CriarEstadoOutput criar(CriarEstadoInput estadoInput) {
        if (estadoRepository.existePorNome(estadoInput.getNome())) {
            throw new BusinessException(ERRO_ESTADO_JA_EXISTE, estadoInput.getNome());
        }

        var pais = buscarPaisService.buscar(estadoInput.getIdPais());

        var estado = estadoMapper.toEstado(estadoInput, pais);

        estadoRepository.criar(estado);

        return estadoMapper.toCriarEstadoOutput(estado);
    }
}
