package com.lucas.projetolojalivros.helper.annotations.pedido;

import com.lucas.projetolojalivros.helper.sql.PedidoSqlFixture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.test.context.jdbc.Sql;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = {PedidoSqlFixture.REMOVER_ITENS, PedidoSqlFixture.REMOVER_PEDIDOS},
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
public @interface RemoverPedidosSql {

}
