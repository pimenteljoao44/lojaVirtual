package com.loja.loja.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.loja.loja.entidades.Categoria;

import java.io.Serializable;
import java.util.Date;

public class CategoriaDTO implements Serializable {

    private Integer id;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataAtualizacao;


    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.dataCriacao = obj.getDataCriacao();
        this.dataAtualizacao = obj.getDataAtualizacao();
    }

    public CategoriaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
