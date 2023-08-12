package com.lucas.projetolojalivros.domain.service.categoria;

import com.lucas.projetolojalivros.domain.model.categoria.Categoria;

public interface BuscarCategoriaService {

    Categoria buscar(Long categoriaId);
}
