package com.lucas.projetolojalivros.application.port.driven.autor;

import com.lucas.projetolojalivros.domain.model.autor.Autor;
import java.util.Optional;

public interface AutorRepository {

    Autor criar(Autor autor);

    Optional<Autor> buscarPorId(Long id);
}
