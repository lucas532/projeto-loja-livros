package com.lucas.projetolojalivros.application.port.driver.livro;

import com.lucas.projetolojalivros.application.dto.output.livro.BuscarLivroOutput;
import java.util.Optional;

public interface BuscarLivroUseCase {

    Optional<BuscarLivroOutput> buscar(Long id);
}
