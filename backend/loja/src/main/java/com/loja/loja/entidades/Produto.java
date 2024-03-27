package com.loja.loja.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer prodId;

    private String descricaoCurta;
    private String descricaoDetalhada;
    private BigDecimal valorCusto;
    private BigDecimal valorVenda;
    private Integer avaliacaoMedia;
    private Boolean ativo;
    private BigDecimal estoque;
    private BigDecimal quantidade = BigDecimal.ZERO;
    private Float tamanho;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataAtualizacao;

    public Produto() {
    }
}
