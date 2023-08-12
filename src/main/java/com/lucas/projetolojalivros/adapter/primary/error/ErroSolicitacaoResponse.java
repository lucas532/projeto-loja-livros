package com.lucas.projetolojalivros.adapter.primary.error;

import java.util.Collection;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
class ErroSolicitacaoResponse {

    String mensagem;
    Collection<ErroValidacao> erros;
}
