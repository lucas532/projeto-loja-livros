package com.lucas.projetolojalivros.domain.model.pais;

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
public class Pais extends BaseEntity<Pais> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq_generator")
    @SequenceGenerator(
        name = "pais_seq_generator",
        sequenceName = "SEQ_PAIS_ID",
        allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

    public Pais(String nome) {
        this.nome = nome;

        validar();
    }
}
