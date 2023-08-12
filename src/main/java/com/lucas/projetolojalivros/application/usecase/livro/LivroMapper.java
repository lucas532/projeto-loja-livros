package com.lucas.projetolojalivros.application.usecase.livro;

import com.lucas.projetolojalivros.application.dto.input.livro.CriarLivroInput;
import com.lucas.projetolojalivros.application.dto.output.livro.BuscarLivroOutput;
import com.lucas.projetolojalivros.application.dto.output.livro.CriarLivroOutput;
import com.lucas.projetolojalivros.application.dto.output.livro.ListarLivrosOutput;
import com.lucas.projetolojalivros.domain.model.autor.Autor;
import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import com.lucas.projetolojalivros.domain.model.livro.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface LivroMapper {

    Livro toLivro(CriarLivroInput criarLivroInput, Autor autor, Categoria categoria);

    CriarLivroOutput toCriarLivroOutput(Livro livro);

    @Mapping(target = "autor", source = "livro.autor.nome")
    @Mapping(target = "categoria", source = "livro.categoria.nome")
    ListarLivrosOutput toListarLivrosOutput(Livro livro);

    @Mapping(target = "autor", source = "livro.autor.nome")
    BuscarLivroOutput toBuscarLivroOutput(Livro livro);
}
