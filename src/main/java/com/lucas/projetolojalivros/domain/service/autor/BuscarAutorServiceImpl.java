package com.lucas.projetolojalivros.domain.service.autor;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_AUTOR_NAO_ENCONTRADO;

import com.lucas.projetolojalivros.application.port.driven.autor.AutorRepository;
import com.lucas.projetolojalivros.domain.model.autor.Autor;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarAutorServiceImpl implements BuscarAutorService {

    private final AutorRepository autorRepository;

    @Override
    public Autor buscar(Long autorId) {
        return autorRepository.buscarPorId(autorId)
            .orElseThrow(
                () -> new BusinessException(ERRO_AUTOR_NAO_ENCONTRADO, autorId.toString()));
    }
}
