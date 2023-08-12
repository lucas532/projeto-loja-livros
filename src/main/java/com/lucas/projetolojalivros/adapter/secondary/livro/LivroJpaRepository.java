package com.lucas.projetolojalivros.adapter.secondary.livro;

import com.lucas.projetolojalivros.domain.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface LivroJpaRepository extends JpaRepository<Livro, Long> {

    boolean existsByTitulo(String titulo);

    boolean existsByIsbn(String isbn);
}
