package com.lucas.projetolojalivros.application.usecase.autor;

import com.lucas.projetolojalivros.application.dto.input.autor.CriarAutorInput;
import com.lucas.projetolojalivros.application.dto.output.autor.CriarAutorOutput;
import com.lucas.projetolojalivros.application.port.driven.autor.AutorRepository;
import com.lucas.projetolojalivros.application.port.driver.autor.CriarAutorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarAutorUseCaseImpl implements CriarAutorUseCase {

    private final AutorMapper autorMapper;
    private final AutorRepository autorRepository;

    @Override
    public CriarAutorOutput criar(CriarAutorInput autorInput) {
        var autor = autorMapper.toAutor(autorInput);

        autorRepository.criar(autor);

        return autorMapper.toCriarAutorOutput(autor);
    }
}
