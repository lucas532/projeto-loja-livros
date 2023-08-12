package com.lucas.projetolojalivros.application.port.driven.estado;

import com.lucas.projetolojalivros.domain.model.estado.Estado;

public interface EstadoRepository {

    Estado criar(Estado estado);

    boolean existePorNome(String nome);

    boolean existePorPais(Long idPais);
}
