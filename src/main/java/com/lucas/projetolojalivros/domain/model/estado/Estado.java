package com.lucas.projetolojalivros.domain.model.estado;

import static lombok.AccessLevel.PROTECTED;

import com.lucas.projetolojalivros.domain.model.base.BaseEntity;
import com.lucas.projetolojalivros.domain.model.pais.Pais;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Estado extends BaseEntity<Estado> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_seq_generator")
    @SequenceGenerator(
        name = "estado_seq_generator",
        sequenceName = "SEQ_ESTADO_ID",
        allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @JoinColumn(name = "ID_PAIS")
    @ManyToOne()
    private Pais pais;

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;

        validar();
    }
}
