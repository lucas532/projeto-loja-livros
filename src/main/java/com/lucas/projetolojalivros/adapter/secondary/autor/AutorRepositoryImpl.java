package com.lucas.projetolojalivros.adapter.secondary.autor;

import com.lucas.projetolojalivros.application.port.driven.autor.AutorRepository;
import com.lucas.projetolojalivros.domain.model.autor.Autor;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AutorRepositoryImpl implements AutorRepository {

    private final AutorJpaRepository autorJpaRepository;

    @Override
    public Autor criar(Autor autor) {
        return this.autorJpaRepository.save(autor);
    }

    @Override
    public Optional<Autor> buscarPorId(Long id) {
        return this.autorJpaRepository.findById(id);
    }
}
