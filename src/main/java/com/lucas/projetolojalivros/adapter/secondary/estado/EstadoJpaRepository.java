package com.lucas.projetolojalivros.adapter.secondary.estado;

import com.lucas.projetolojalivros.domain.model.estado.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EstadoJpaRepository extends JpaRepository<Estado, Long> {

    boolean existsByNome(String nome);

    boolean existsByPaisId(Long idPais);
}
