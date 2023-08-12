package com.lucas.projetolojalivros.application.dto;

import static com.lucas.projetolojalivros.helper.RandomHelper.gerarInteger;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarLong;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarString;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarStringEmail;
import static com.lucas.projetolojalivros.helper.RandomHelper.gerarStringNumerico;

import com.lucas.projetolojalivros.application.dto.input.pedido.CriarPedidoInput;
import com.lucas.projetolojalivros.application.dto.input.pedido.ItemPedidoDto;
import java.util.Collection;
import java.util.List;

public class CriarPedidoInputFixture {

    public static CriarPedidoInput.CriarPedidoInputBuilder builder() {

        return CriarPedidoInput.builder()
            .email(gerarStringEmail())
            .nome(gerarString())
            .sobrenome(gerarString())
            .cpfCnpj(gerarStringNumerico(11))
            .endereco(gerarString())
            .complemento(gerarString())
            .cidade(gerarString())
            .telefone(gerarString())
            .cep(gerarString())
            .idPais(gerarLong())
            .idEstado(gerarLong())
            .itens(buildItens());
    }

    public static ItemPedidoDto.ItemPedidoDtoBuilder buildItem() {
        return ItemPedidoDto.builder()
            .quantidade(gerarInteger())
            .idLivro(gerarLong());
    }

    private static Collection<ItemPedidoDto> buildItens() {
        return List.of(buildItem().build());
    }
}
