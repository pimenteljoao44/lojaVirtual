package com.loja.loja.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.loja.loja.dto.UserDTO;
import com.loja.loja.entidades.enums.NivelAcesso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable(false)
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String nome;

    @NotEmpty(message = "Campo Login é requerido")
    private String login;

    @NotEmpty(message = "Campo Senha é requerido")
    private String senha;
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    private Integer nivelAcesso;

    public Usuario() {
        super();
    }

    public Usuario(Integer id, String nome, String login, String senha, NivelAcesso nivelAcesso,Pessoa pessoa) {
        super();
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.nivelAcesso = (nivelAcesso == null? 0: nivelAcesso.getCod());
        this.pessoa = pessoa;
    }

    public Usuario(UserDTO objDTO) {
        this.nome = objDTO.getNome();
        this.login = objDTO.getLogin();
        this.senha = objDTO.getSenha();
        this.nivelAcesso = (objDTO.getNivelAcesso() == null? 0: objDTO.getNivelAcesso().getCod());
        this.pessoa = objDTO.getPessoa();
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public NivelAcesso getNivelAcesso() {
        return NivelAcesso.toEnum(this.nivelAcesso);
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso.getCod();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, nivelAcesso, nome, senha);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(id, other.id) && Objects.equals(login, other.login) && nivelAcesso == other.nivelAcesso
                && Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(Objects.equals(nivelAcesso, NivelAcesso.ADMIN.getCod())) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"))	;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}