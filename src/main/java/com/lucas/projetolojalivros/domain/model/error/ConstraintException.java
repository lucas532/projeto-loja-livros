package com.lucas.projetolojalivros.domain.model.error;

public class ConstraintException extends LojaLivroException {

    public ConstraintException(ErroDeNegocio erro, String parametros) {
        super(erro.getValue() + parametros);
    }
}
