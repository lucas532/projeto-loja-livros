package com.lucas.projetolojalivros.helper.annotations.pais;

import com.lucas.projetolojalivros.helper.sql.PaisSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = PaisSqlFixture.REMOVER_PAISES,
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
public @interface RemoverPaisesSql {

}
