package com.lucas.projetolojalivros.domain.model.error;

public class BusinessException extends LojaLivroException {

    public BusinessException(ErroDeNegocio erroEnum, String parametro) {
        super(erroEnum.getValue() + parametro);
    }
}
