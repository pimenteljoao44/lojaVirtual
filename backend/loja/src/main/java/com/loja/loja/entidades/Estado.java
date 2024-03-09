package com.loja.loja.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "estado")
@Data
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer estId;

    private String nome;
    private String sigla;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cidade> cidades;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    public Estado() {
        this.cidades = new ArrayList<>();
    }

    public Estado(Integer estId, String nome, String sigla, Date dataCriacao, Date dataAtualizacao) {
        this.estId = estId;
        this.nome = nome;
        this.sigla = sigla;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
}