package com.lucas.projetolojalivros.helper.annotations.pais;

import com.lucas.projetolojalivros.helper.sql.PaisSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = PaisSqlFixture.INSERIR_PAIS,
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public @interface InserirPaisSql {

}
