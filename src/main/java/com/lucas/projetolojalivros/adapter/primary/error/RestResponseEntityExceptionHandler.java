package com.lucas.projetolojalivros.adapter.primary.error;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import com.lucas.projetolojalivros.domain.model.error.BusinessException;
import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
        var bodyOfResponse = "Erro de negócio - " + ex.getMessage();

        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            new HttpHeaders(),
            INTERNAL_SERVER_ERROR,
            request);
    }

    @ExceptionHandler(ConstraintException.class)
    protected ResponseEntity<Object> handleConstraint(ConstraintException ex, WebRequest request) {
        var mensagemRetorno = ErroSolicitacaoResponseFixture.build(ex);

        return handleExceptionInternal(
            ex,
            mensagemRetorno,
            new HttpHeaders(),
            UNPROCESSABLE_ENTITY,
            request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        var mensagemRetorno = ErroSolicitacaoResponseFixture.build(fieldErrors);

        return handleExceptionInternal(
            ex,
            mensagemRetorno,
            headers,
            UNPROCESSABLE_ENTITY,
            request);
    }
}
