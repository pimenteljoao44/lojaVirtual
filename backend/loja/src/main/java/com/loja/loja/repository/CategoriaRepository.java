package com.loja.loja.repository;

import com.loja.loja.entidades.Categoria;
import com.loja.loja.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

    @Query("SELECT obj FROM Categoria obj WHERE obj.nome =:nome")
    Categoria findByName(@Param("nome") String nome);
}
