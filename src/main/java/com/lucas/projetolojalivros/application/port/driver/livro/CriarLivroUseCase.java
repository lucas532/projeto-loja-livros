package com.lucas.projetolojalivros.application.port.driver.livro;

import com.lucas.projetolojalivros.application.dto.input.livro.CriarLivroInput;
import com.lucas.projetolojalivros.application.dto.output.livro.CriarLivroOutput;

public interface CriarLivroUseCase {

    CriarLivroOutput criar(CriarLivroInput livroInput);
}
