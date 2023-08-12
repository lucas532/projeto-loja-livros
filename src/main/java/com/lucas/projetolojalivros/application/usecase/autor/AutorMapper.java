package com.lucas.projetolojalivros.application.usecase.autor;

import com.lucas.projetolojalivros.application.dto.input.autor.CriarAutorInput;
import com.lucas.projetolojalivros.application.dto.output.autor.CriarAutorOutput;
import com.lucas.projetolojalivros.domain.model.autor.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface AutorMapper {

    Autor toAutor(CriarAutorInput criarAutorInput);

    CriarAutorOutput toCriarAutorOutput(Autor autor);
}
