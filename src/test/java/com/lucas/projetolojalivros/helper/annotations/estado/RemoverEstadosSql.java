package com.lucas.projetolojalivros.helper.annotations.estado;

import com.lucas.projetolojalivros.helper.sql.EstadoSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = EstadoSqlFixture.REMOVER_ESTADOS,
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
public @interface RemoverEstadosSql {

}
