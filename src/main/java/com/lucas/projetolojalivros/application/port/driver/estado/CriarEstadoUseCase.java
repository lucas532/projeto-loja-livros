package com.lucas.projetolojalivros.application.port.driver.estado;

import com.lucas.projetolojalivros.application.dto.input.estado.CriarEstadoInput;
import com.lucas.projetolojalivros.application.dto.output.estado.CriarEstadoOutput;

public interface CriarEstadoUseCase {

    CriarEstadoOutput criar(CriarEstadoInput estadoInput);
}
