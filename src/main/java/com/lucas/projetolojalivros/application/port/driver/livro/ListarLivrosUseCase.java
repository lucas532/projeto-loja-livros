package com.lucas.projetolojalivros.application.port.driver.livro;

import com.lucas.projetolojalivros.application.dto.output.livro.ListarLivrosOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarLivrosUseCase {

    Page<ListarLivrosOutput> listar(Pageable paginacao);
}
