package com.lucas.projetolojalivros.application.usecase.livro;

import com.lucas.projetolojalivros.application.dto.output.livro.ListarLivrosOutput;
import com.lucas.projetolojalivros.application.port.driven.livro.LivroRepository;
import com.lucas.projetolojalivros.application.port.driver.livro.ListarLivrosUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarLivrosUseCaseImpl implements ListarLivrosUseCase {

    private final LivroMapper livroMapper;
    private final LivroRepository livroRepository;

    @Override
    public Page<ListarLivrosOutput> listar(Pageable paginacao) {
        var pageLivros = livroRepository.listar(paginacao);
        return pageLivros.map(livroMapper::toListarLivrosOutput);
    }
}
