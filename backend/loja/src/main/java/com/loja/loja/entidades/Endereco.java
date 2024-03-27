package com.loja.loja.entidades;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "endereco")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "enderecoId")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer enderecoId;

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    @ManyToOne()
    @JoinColumn(name = "cidId", nullable = true)
    private Cidade cidade;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE,CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @JsonBackReference
    private Pessoa pessoa;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataAtualizacao;

    public Endereco() {

    }

    @Override
    public String toString() {
        return "Endereco{" +
                "enderecoId=" + enderecoId +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade=" + (cidade != null ? cidade.getCidId() : null) + // Adjust as per your Cidade class
                ", pessoa=" + (pessoa != null ? pessoa.getId() : null) + // Adjust as per your Pessoa class
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}
