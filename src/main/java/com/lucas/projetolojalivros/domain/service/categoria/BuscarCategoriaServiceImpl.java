package com.lucas.projetolojalivros.domain.service.categoria;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_CATEGORIA_NAO_ENCONTRADA;

import com.lucas.projetolojalivros.application.port.driven.categoria.CategoriaRepository;
import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarCategoriaServiceImpl implements BuscarCategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public Categoria buscar(Long categoriaId) {
        return categoriaRepository.buscarPorId(categoriaId)
            .orElseThrow(
                () -> new BusinessException(ERRO_CATEGORIA_NAO_ENCONTRADA, categoriaId.toString()));
    }
}
