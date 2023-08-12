package com.lucas.projetolojalivros.application.usecase.livro;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_CRIAR_LIVRO;

import com.lucas.projetolojalivros.application.dto.input.livro.CriarLivroInput;
import com.lucas.projetolojalivros.application.dto.output.livro.CriarLivroOutput;
import com.lucas.projetolojalivros.application.port.driven.livro.LivroRepository;
import com.lucas.projetolojalivros.application.port.driver.livro.CriarLivroUseCase;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.service.autor.BuscarAutorService;
import com.lucas.projetolojalivros.domain.service.categoria.BuscarCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarLivroUseCaseImpl implements CriarLivroUseCase {

    private final LivroMapper livroMapper;
    private final LivroRepository livroRepository;
    private final BuscarAutorService buscarAutorService;
    private final BuscarCategoriaService buscarCategoriaService;

    @Override
    public CriarLivroOutput criar(CriarLivroInput livroInput) {
        if (livroRepository.existePorTitulo(livroInput.getTitulo())) {
            throw new BusinessException(ERRO_CRIAR_LIVRO, "Título já cadastrado");
        }
        if (livroRepository.existePorIsbn(livroInput.getIsbn())) {
            throw new BusinessException(ERRO_CRIAR_LIVRO, "Isbn já cadastrado");
        }

        var autor = buscarAutorService.buscar(livroInput.getIdAutor());
        var categoria = buscarCategoriaService.buscar(livroInput.getIdCategoria());

        var livro = livroMapper.toLivro(livroInput, autor, categoria);

        livroRepository.criar(livro);

        return livroMapper.toCriarLivroOutput(livro);
    }
}
