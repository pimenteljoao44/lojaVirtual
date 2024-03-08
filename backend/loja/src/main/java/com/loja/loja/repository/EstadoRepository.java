package com.loja.loja.repository;

import com.loja.loja.entidades.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {
    boolean existsByNomeOrSigla(String nome, String sigla);
}
