package com.lucas.projetolojalivros.helper.annotations.autor;

import com.lucas.projetolojalivros.helper.sql.AutorSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = AutorSqlFixture.INSERIR_AUTOR,
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public @interface InserirAutorSql {

}
