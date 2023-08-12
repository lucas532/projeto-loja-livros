package com.lucas.projetolojalivros.application.usecase.livro;

import com.lucas.projetolojalivros.application.dto.output.livro.BuscarLivroOutput;
import com.lucas.projetolojalivros.application.port.driven.livro.LivroRepository;
import com.lucas.projetolojalivros.application.port.driver.livro.BuscarLivroUseCase;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarLivroUseCaseImpl implements BuscarLivroUseCase {

    private final LivroMapper livroMapper;
    private final LivroRepository livroRepository;

    @Override
    public Optional<BuscarLivroOutput> buscar(Long id) {
        var optionalLivro = livroRepository.buscarPorId(id);

        return optionalLivro.map(livroMapper::toBuscarLivroOutput);
    }
}
