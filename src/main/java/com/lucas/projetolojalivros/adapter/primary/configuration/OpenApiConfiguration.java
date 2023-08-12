package com.lucas.projetolojalivros.adapter.primary.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "API de loja de livros",
        version = "1.0",
        description = "API responsável por loja de livros",
        contact = @Contact(
            name = "Lucas Lopes",
            email = "lucas.lopes@db.tec.br"
        ),
        license = @License(
            name = "MIT Licence"
        )
    ),
    servers = @Server(
        url = "/"
    )
)
@Configuration
class OpenApiConfiguration {

}
