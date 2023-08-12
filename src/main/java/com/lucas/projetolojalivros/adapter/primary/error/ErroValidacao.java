package com.lucas.projetolojalivros.adapter.primary.error;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
class ErroValidacao {

    String campo;
    String mensagem;
}
