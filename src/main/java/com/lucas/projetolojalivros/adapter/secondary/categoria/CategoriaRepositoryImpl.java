package com.lucas.projetolojalivros.adapter.secondary.categoria;

import com.lucas.projetolojalivros.application.port.driven.categoria.CategoriaRepository;
import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final CategoriaJpaRepository categoriaJpaRepository;

    @Override
    public Categoria criar(Categoria categoria) {
        return this.categoriaJpaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return this.categoriaJpaRepository.findById(id);
    }

    @Override
    public boolean existePorNome(String nome) {
        return this.categoriaJpaRepository.existsByNome(nome);
    }
}
