package com.lucas.projetolojalivros.application.usecase.estado;

import com.lucas.projetolojalivros.application.dto.input.estado.CriarEstadoInput;
import com.lucas.projetolojalivros.application.dto.output.estado.CriarEstadoOutput;
import com.lucas.projetolojalivros.domain.model.estado.Estado;
import com.lucas.projetolojalivros.domain.model.pais.Pais;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface EstadoMapper {

    @Mapping(target = "nome", source = "criarEstadoInput.nome")
    Estado toEstado(CriarEstadoInput criarEstadoInput, Pais pais);

    CriarEstadoOutput toCriarEstadoOutput(Estado estado);
}
