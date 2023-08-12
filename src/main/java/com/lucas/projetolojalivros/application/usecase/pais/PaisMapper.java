package com.lucas.projetolojalivros.application.usecase.pais;

import com.lucas.projetolojalivros.application.dto.input.pais.CriarPaisInput;
import com.lucas.projetolojalivros.application.dto.output.pais.CriarPaisOutput;
import com.lucas.projetolojalivros.domain.model.pais.Pais;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface PaisMapper {

    Pais toPais(CriarPaisInput criarLivroInput);

    CriarPaisOutput toCriarPaisOutput(Pais pais);
}
