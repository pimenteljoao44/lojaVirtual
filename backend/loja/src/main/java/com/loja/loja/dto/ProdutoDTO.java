package com.loja.loja.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.loja.loja.entidades.Categoria;
import com.loja.loja.entidades.Produto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProdutoDTO implements Serializable {

    private Integer prodId;
    @NotEmpty(message = "O Campo Descrição curta é requerido.")
    private String descricaoCurta;
    private String descricaoDetalhada;
    @NotNull(message = "O campo Categoria é requerido.")
    private Categoria categoria;
    @NotNull(message = "O campo Valor de custo é requrido.")
    private BigDecimal valorCusto = BigDecimal.ZERO;
    @NotNull(message = "O campo Valor de venda é requrido.")
    private BigDecimal valorVenda = BigDecimal.ZERO;
    private Integer avaliacaoMedia;
    private Boolean ativo;
    private BigDecimal estoque;
    private BigDecimal quantidade = BigDecimal.ZERO;
    private Float tamanho;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataAtualizacao;

    public ProdutoDTO(Produto obj) {
        this.prodId = obj.getProdId();
        this.descricaoCurta = obj.getDescricaoCurta();
        this.descricaoDetalhada = obj.getDescricaoDetalhada();
        this.categoria = obj.getCategoria();
        this.valorCusto = obj.getValorCusto();
        this.valorVenda = obj.getValorVenda();
        this.avaliacaoMedia = obj.getAvaliacaoMedia();
        this.ativo = obj.getAtivo();
        this.estoque = obj.getEstoque();
        this.quantidade = obj.getQuantidade();
        this.tamanho = obj.getTamanho();
        this.dataCriacao = obj.getDataCriacao();
        this.dataAtualizacao = obj.getDataAtualizacao();
    }

    public ProdutoDTO() {
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getDescricaoCurta() {
        return descricaoCurta;
    }

    public void setDescricaoCurta(String descricaoCurta) {
        this.descricaoCurta = descricaoCurta;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Integer getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public void setAvaliacaoMedia(Integer avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Float getTamanho() {
        return tamanho;
    }

    public void setTamanho(Float tamanho) {
        this.tamanho = tamanho;
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
