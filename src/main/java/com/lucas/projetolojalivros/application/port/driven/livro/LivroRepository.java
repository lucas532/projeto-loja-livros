package com.lucas.projetolojalivros.application.port.driven.livro;

import com.lucas.projetolojalivros.domain.model.livro.Livro;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LivroRepository {

    Livro criar(Livro livro);

    boolean existePorTitulo(String titulo);

    boolean existePorIsbn(String isbn);

    Page<Livro> listar(Pageable pageable);

    Optional<Livro> buscarPorId(Long id);
}
