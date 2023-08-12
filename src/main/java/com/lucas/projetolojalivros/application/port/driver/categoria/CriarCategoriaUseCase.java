package com.lucas.projetolojalivros.application.port.driver.categoria;

import com.lucas.projetolojalivros.application.dto.input.categoria.CriarCategoriaInput;
import com.lucas.projetolojalivros.application.dto.output.categoria.CriarCategoriaOutput;

public interface CriarCategoriaUseCase {

    CriarCategoriaOutput criar(CriarCategoriaInput categoriaInput);
}
