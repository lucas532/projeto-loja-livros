package com.lucas.projetolojalivros.domain.model.pedido;

import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;

import com.lucas.projetolojalivros.domain.model.base.BaseEntity;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Pedido extends BaseEntity<Pedido> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq_generator")
    @SequenceGenerator(
        name = "pedido_seq_generator",
        sequenceName = "SEQ_PEDIDO_ID",
        allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Pattern(regexp = "([\\d]{11})|([\\d]{14})")
    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @NotNull
    @Column(name = "DATA_PEDIDO")
    private LocalDate dataPedido;

    @NotEmpty
    @OneToMany(
        mappedBy = "pedido",
        cascade = ALL,
        orphanRemoval = true
    )
    private Collection<Item> itens;

    public Pedido(String cpfCnpj, Collection<Item> itens) {
        this.cpfCnpj = cpfCnpj;
        this.dataPedido = LocalDate.now();
        this.itens = itens;

        validar();
    }
}
