package com.lucas.projetolojalivros.domain.model.pedido;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import com.lucas.projetolojalivros.domain.model.base.BaseEntity;
import com.lucas.projetolojalivros.domain.model.livro.Livro;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Item extends BaseEntity<Item> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_generator")
    @SequenceGenerator(
        name = "item_seq_generator",
        sequenceName = "SEQ_ITEM_ID",
        allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull
    @OneToOne
    private Livro livro;
    @ManyToOne(fetch = LAZY)
    private Pedido pedido;

    public Item(Integer quantidade, Livro livro) {
        this.quantidade = quantidade;
        this.livro = livro;

        validar();
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
