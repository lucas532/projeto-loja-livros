package com.lucas.projetolojalivros.adapter.secondary.pais;

import com.lucas.projetolojalivros.domain.model.pais.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PaisJpaRepository extends JpaRepository<Pais, Long> {

    boolean existsByNome(String nome);
}
