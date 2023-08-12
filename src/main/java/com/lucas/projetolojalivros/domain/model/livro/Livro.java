package com.lucas.projetolojalivros.domain.model.livro;

import static lombok.AccessLevel.PROTECTED;

import com.lucas.projetolojalivros.domain.model.autor.Autor;
import com.lucas.projetolojalivros.domain.model.base.BaseEntity;
import com.lucas.projetolojalivros.domain.model.categoria.Categoria;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Livro extends BaseEntity<Livro> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_seq_generator")
    @SequenceGenerator(
        name = "livro_seq_generator",
        sequenceName = "SEQ_LIVRO_ID",
        allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "TITULO")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    @Column(name = "RESUMO")
    private String resumo;

    @Column(name = "SUMARIO")
    private String sumario;

    @NotNull
    @DecimalMin("20.00")
    @Column(name = "PRECO")
    private BigDecimal preco;

    @NotNull
    @Min(100)
    @Column(name = "NUMERO_PAGINAS")
    private Integer numeroPaginas;

    @NotBlank
    @Column(name = "ISBN")
    private String isbn;

    @NotNull
    @Column(name = "DATA_PUBLICACAO")
    private LocalDate dataPublicacao;

    @NotNull
    @JoinColumn(name = "ID_AUTOR")
    @ManyToOne()
    private Autor autor;

    @NotNull
    @JoinColumn(name = "ID_CATEGORIA")
    @ManyToOne()
    private Categoria categoria;

    public Livro(String titulo, String resumo, String sumario, BigDecimal preco,
        Integer numeroPaginas, String isbn, LocalDate dataPublicacao, Autor autor,
        Categoria categoria) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
        this.categoria = categoria;

        validar();
    }
}
