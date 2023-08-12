package com.lucas.projetolojalivros.application.port.driver.pais;

import com.lucas.projetolojalivros.application.dto.input.pais.CriarPaisInput;
import com.lucas.projetolojalivros.application.dto.output.pais.CriarPaisOutput;

public interface CriarPaisUseCase {

    CriarPaisOutput criar(CriarPaisInput paisInput);
}
