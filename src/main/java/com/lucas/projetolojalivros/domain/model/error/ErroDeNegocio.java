package com.lucas.projetolojalivros.domain.model.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErroDeNegocio {

    ERRO_VALIDAR_CONSTRAINTS("Erro ao validar constraints: "),
    ERRO_AUTOR_NAO_ENCONTRADO("Autor não encontrado: "),
    ERRO_CATEGORIA_NAO_ENCONTRADA("Categoria não encontrada: "),
    ERRO_PAIS_NAO_ENCONTRADO("País não encontrado: "),
    ERRO_LIVRO_NAO_ENCONTRADO("Livro não encontrado: "),
    ERRO_CATEGORIA_JA_EXISTE("Categoria já existe: "),
    ERRO_PAIS_JA_EXISTE("País já existe: "),
    ERRO_ESTADO_JA_EXISTE("Estado já existe: "),
    ERRO_CRIAR_LIVRO("Erro ao criar livro: "),
    ERRO_CRIAR_PEDIDO("Erro ao criar pedido: ");


    private final String value;
}
