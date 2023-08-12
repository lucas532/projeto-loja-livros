package com.lucas.projetolojalivros.helper.annotations.livro;

import com.lucas.projetolojalivros.helper.sql.AutorSqlFixture;
import com.lucas.projetolojalivros.helper.sql.CategoriaSqlFixture;
import com.lucas.projetolojalivros.helper.sql.LivroSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = {LivroSqlFixture.REMOVER_LIVROS, AutorSqlFixture.REMOVER_AUTORES,
    CategoriaSqlFixture.REMOVER_CATEGORIAS},
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
public @interface RemoverLivrosSql {

}
