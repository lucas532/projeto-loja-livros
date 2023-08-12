package com.lucas.projetolojalivros.adapter.secondary.livro;

import com.lucas.projetolojalivros.application.port.driven.livro.LivroRepository;
import com.lucas.projetolojalivros.domain.model.livro.Livro;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LivroRepositoryImpl implements LivroRepository {

    private final LivroJpaRepository livroJpaRepository;

    @Override
    public Livro criar(Livro livro) {
        return this.livroJpaRepository.save(livro);
    }

    @Override
    public boolean existePorTitulo(String titulo) {
        return this.livroJpaRepository.existsByTitulo(titulo);
    }

    @Override
    public boolean existePorIsbn(String isbn) {
        return this.livroJpaRepository.existsByIsbn(isbn);
    }

    @Override
    public Page<Livro> listar(Pageable pageable) {
        return this.livroJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        return this.livroJpaRepository.findById(id);
    }
}
