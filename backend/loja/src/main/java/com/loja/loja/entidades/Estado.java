package com.loja.loja.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name = "estado")
@Data
public class Estado {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer estId;
    private String nome;
    private String sigla;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    public Estado() {
    }

    public Estado(Integer estId, String nome, String sigla, Date dataCriacao, Date dataAtualizacao) {
        this.estId = estId;
        this.nome = nome;
        this.sigla = sigla;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
}
