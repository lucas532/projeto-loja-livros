package com.lucas.projetolojalivros.adapter.secondary.categoria;

import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoriaJpaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNome(String nome);
}
