package com.lucas.projetolojalivros.domain.service.autor;

import com.lucas.projetolojalivros.domain.model.autor.Autor;

public interface BuscarAutorService {

    Autor buscar(Long autorId);
}
