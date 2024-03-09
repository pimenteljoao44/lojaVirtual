package com.loja.loja.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "cidade")
@Data
public class Cidade {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cidId;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "estId", nullable = true)
    @JsonBackReference
    private Estado estado;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataAtualizacao;

    public Cidade() {
    }

    public Cidade(Integer cidId, String nome, Date dataCriacao, Date dataAtualizacao) {
        this.cidId = cidId;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
}