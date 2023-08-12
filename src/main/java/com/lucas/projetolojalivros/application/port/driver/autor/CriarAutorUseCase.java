package com.lucas.projetolojalivros.application.port.driver.autor;

import com.lucas.projetolojalivros.application.dto.input.autor.CriarAutorInput;
import com.lucas.projetolojalivros.application.dto.output.autor.CriarAutorOutput;

public interface CriarAutorUseCase {

    CriarAutorOutput criar(CriarAutorInput autorInput);
}
