package com.lucas.projetolojalivros.domain.model.autor;

import static lombok.AccessLevel.PROTECTED;

import com.lucas.projetolojalivros.domain.model.base.BaseEntity;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Autor extends BaseEntity<Autor> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_seq_generator")
    @SequenceGenerator(
        name = "autor_seq_generator",
        sequenceName = "SEQ_AUTOR_ID",
        allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

    @NotBlank
    @Email
    @Column(name = "EMAIL")
    private String email;

    @Size(max = 300)
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "CRIADO_EM")
    private LocalDate criadoEm;

    public Autor(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.criadoEm = LocalDate.now();

        validar();
    }
}
