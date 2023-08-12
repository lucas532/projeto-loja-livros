package com.lucas.projetolojalivros.adapter.secondary.autor;

import com.lucas.projetolojalivros.domain.model.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AutorJpaRepository extends JpaRepository<Autor, Long> {

}
