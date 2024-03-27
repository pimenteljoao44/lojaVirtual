package com.loja.loja.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "cidade")
@Data
@AllArgsConstructor
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cidId;

    private String nome;

    @ManyToOne(cascade = CascadeType.PERSIST)
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

    @Override
    public String toString() {
        return "Cidade{" +
                "cidadeId=" + cidId +
                ", nome='" + nome + '\'' +
                ", estado=" + (estado != null ? estado.getEstId(): null) +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}