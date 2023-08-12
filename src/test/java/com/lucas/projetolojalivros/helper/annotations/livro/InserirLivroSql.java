package com.lucas.projetolojalivros.helper.annotations.livro;

import com.lucas.projetolojalivros.helper.sql.AutorSqlFixture;
import com.lucas.projetolojalivros.helper.sql.CategoriaSqlFixture;
import com.lucas.projetolojalivros.helper.sql.LivroSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = {AutorSqlFixture.INSERIR_AUTOR, CategoriaSqlFixture.INSERIR_CATEGORIA,
    LivroSqlFixture.INSERIR_LIVRO},
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
public @interface InserirLivroSql {

}
