package com.lucas.projetolojalivros.helper.annotations.categoria;

import com.lucas.projetolojalivros.helper.sql.CategoriaSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = CategoriaSqlFixture.REMOVER_CATEGORIAS,
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
public @interface RemoverCategoriasSql {

}
