package com.lucas.projetolojalivros.application.usecase.categoria;

import com.lucas.projetolojalivros.application.dto.input.categoria.CriarCategoriaInput;
import com.lucas.projetolojalivros.application.dto.output.categoria.CriarCategoriaOutput;
import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface CategoriaMapper {

    Categoria toCategoria(CriarCategoriaInput criarCategoriaInput);

    CriarCategoriaOutput toCriarCategoriaOutput(Categoria categoria);
}
