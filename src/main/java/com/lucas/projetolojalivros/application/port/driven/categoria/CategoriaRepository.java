package com.lucas.projetolojalivros.application.port.driven.categoria;

import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import java.util.Optional;

public interface CategoriaRepository {

    Categoria criar(Categoria categoria);

    Optional<Categoria> buscarPorId(Long id);

    boolean existePorNome(String nome);
}
