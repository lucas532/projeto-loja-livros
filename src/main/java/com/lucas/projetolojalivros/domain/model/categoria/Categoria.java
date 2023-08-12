package com.lucas.projetolojalivros.domain.model.categoria;

import static lombok.AccessLevel.PROTECTED;

import com.lucas.projetolojalivros.domain.model.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Categoria extends BaseEntity<Categoria> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq_generator")
    @SequenceGenerator(
        name = "categoria_seq_generator",
        sequenceName = "SEQ_CATEGORIA_ID",
        allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;

        validar();
    }
}
