package com.loja.loja.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.loja.loja.entidades.Endereco;
import com.loja.loja.entidades.Pessoa;
import com.loja.loja.entidades.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.Date;

public class PessoaDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "O campo Nome é requerido!")
    private String nome;
    @NotEmpty(message = "O campo cpf é requerido")
    @CPF
    private String cpf;
    private Endereco endereco;
    @NotEmpty(message = "O campo Telefone é requerido!")
    private String telefone;
    @NotEmpty(message = "O campo email é requerido")
    @Email
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataAtualizacao;

    public PessoaDTO() {
    }

    public PessoaDTO(Pessoa obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.endereco = obj.getEndereco();
        this.telefone = obj.getTelefone();
        this.email = obj.getEmail();
        this.dataCriacao = obj.getDataCriacao();
        this.dataAtualizacao = obj.getDataAtualizacao();
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
