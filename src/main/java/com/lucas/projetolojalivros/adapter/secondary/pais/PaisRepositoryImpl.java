package com.lucas.projetolojalivros.adapter.secondary.pais;

import com.lucas.projetolojalivros.application.port.driven.pais.PaisRepository;
import com.lucas.projetolojalivros.domain.model.pais.Pais;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaisRepositoryImpl implements PaisRepository {

    private final PaisJpaRepository paisJpaRepository;

    @Override
    public Pais criar(Pais pais) {
        return this.paisJpaRepository.save(pais);
    }

    @Override
    public boolean existePorNome(String nome) {
        return this.paisJpaRepository.existsByNome(nome);
    }

    @Override
    public Optional<Pais> buscarPorId(Long id) {
        return this.paisJpaRepository.findById(id);
    }
}
