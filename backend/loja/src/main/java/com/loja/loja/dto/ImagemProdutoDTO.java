package com.loja.loja.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.loja.loja.entidades.ImagemProduto;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

public class ImagemProdutoDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "O  campo nome é requerido")
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataAtualizacao;

    public ImagemProdutoDTO(ImagemProduto obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.dataCriacao = obj.getDataCriacao();
        this.dataAtualizacao = obj.getDataAtualizacao();
    }

    public ImagemProdutoDTO() {
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
