package com.loja.loja.dto;

import com.loja.loja.entidades.Pessoa;
import com.loja.loja.entidades.Usuario;
import com.loja.loja.entidades.enums.NivelAcesso;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nome;

    @NotEmpty(message = "Campo Login é requerido")
    private String login;

    @NotEmpty(message = "Campo Senha é requerido")
    private String senha;

    private Integer nivelAcesso;

    private Pessoa pessoa;

    public UserDTO() {
        super();
    }

    public UserDTO(Usuario obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.login = obj.getLogin();
        this.senha = obj.getSenha();
        this.nivelAcesso = obj.getNivelAcesso().getCod() != null
                ? obj.getNivelAcesso().getCod() : 0;
        this.pessoa = obj.getPessoa();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public NivelAcesso getNivelAcesso() {
        return NivelAcesso.toEnum(this.nivelAcesso);
    }

    public void setNivelAcesso(Integer nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
