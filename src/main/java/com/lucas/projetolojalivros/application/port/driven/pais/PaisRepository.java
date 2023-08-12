package com.lucas.projetolojalivros.application.port.driven.pais;

import com.lucas.projetolojalivros.domain.model.pais.Pais;
import java.util.Optional;

public interface PaisRepository {

    Pais criar(Pais pais);

    boolean existePorNome(String nome);

    Optional<Pais> buscarPorId(Long id);
}
