package com.lucas.projetolojalivros.helper.annotations.categoria;

import com.lucas.projetolojalivros.helper.sql.CategoriaSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = CategoriaSqlFixture.INSERIR_CATEGORIA,
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public @interface InserirCategoriaSql {

}
