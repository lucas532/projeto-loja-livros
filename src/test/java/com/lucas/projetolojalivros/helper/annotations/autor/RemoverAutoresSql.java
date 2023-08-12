package com.lucas.projetolojalivros.helper.annotations.autor;

import com.lucas.projetolojalivros.helper.sql.AutorSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = AutorSqlFixture.REMOVER_AUTORES,
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
public @interface RemoverAutoresSql {

}
