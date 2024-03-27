package com.loja.loja.repository;

import com.loja.loja.entidades.ImagemProduto;
import com.loja.loja.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto,Integer> {
    @Query("SELECT obj FROM ImagemProduto obj WHERE obj.nome =:nome")
    ImagemProduto findByName(@Param("nome") String nome);
}
