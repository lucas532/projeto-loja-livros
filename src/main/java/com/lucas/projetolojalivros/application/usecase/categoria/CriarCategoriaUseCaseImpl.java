package com.lucas.projetolojalivros.application.usecase.categoria;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_CATEGORIA_JA_EXISTE;

import com.lucas.projetolojalivros.application.dto.input.categoria.CriarCategoriaInput;
import com.lucas.projetolojalivros.application.dto.output.categoria.CriarCategoriaOutput;
import com.lucas.projetolojalivros.application.port.driven.categoria.CategoriaRepository;
import com.lucas.projetolojalivros.application.port.driver.categoria.CriarCategoriaUseCase;
import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarCategoriaUseCaseImpl implements CriarCategoriaUseCase {

    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository categoriaRepository;

    @Override
    public CriarCategoriaOutput criar(CriarCategoriaInput categoriaInput) {
        if (categoriaRepository.existePorNome(categoriaInput.getNome())) {
            throw new BusinessException(ERRO_CATEGORIA_JA_EXISTE, categoriaInput.getNome());
        }

        var categoria = categoriaMapper.toCategoria(categoriaInput);

        categoriaRepository.criar(categoria);

        return categoriaMapper.toCriarCategoriaOutput(categoria);
    }
}
