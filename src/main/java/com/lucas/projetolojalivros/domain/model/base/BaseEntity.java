package com.lucas.projetolojalivros.domain.model.base;

import static com.lucas.projetolojalivros.domain.model.error.ErroDeNegocio.ERRO_VALIDAR_CONSTRAINTS;

import com.lucas.projetolojalivros.domain.model.error.ConstraintException;
import java.util.Set;
import javax.persistence.MappedSuperclass;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.data.domain.AbstractAggregateRoot;

@MappedSuperclass
public abstract class BaseEntity<T extends BaseEntity<T>> extends AbstractAggregateRoot<T> {

    private static <T> void lancarExcecao(Set<ConstraintViolation<T>> errors) {
        if (!errors.isEmpty()) {
            var ex = new ConstraintViolationException(errors);
            throw new ConstraintException(ERRO_VALIDAR_CONSTRAINTS, ex.getMessage());
        }
    }

    public final void validar() {
        Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

        Set<ConstraintViolation<BaseEntity<T>>> violations = validator.validate(this);
        lancarExcecao(violations);
    }
}
