package com.lucas.projetolojalivros.domain.model.error;

public class LojaLivroException extends RuntimeException {

    public LojaLivroException(String mensagem) {
        super(mensagem);
    }
}
