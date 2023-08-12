package com.lucas.projetolojalivros.adapter.secondary.estado;

import com.lucas.projetolojalivros.application.port.driven.estado.EstadoRepository;
import com.lucas.projetolojalivros.domain.model.estado.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EstadoRepositoryImpl implements EstadoRepository {

    private final EstadoJpaRepository estadoJpaRepository;

    @Override
    public Estado criar(Estado estado) {
        return this.estadoJpaRepository.save(estado);
    }

    @Override
    public boolean existePorNome(String nome) {
        return this.estadoJpaRepository.existsByNome(nome);
    }

    @Override
    public boolean existePorPais(Long idPais) {
        return this.estadoJpaRepository.existsByPaisId(idPais);
    }
}
