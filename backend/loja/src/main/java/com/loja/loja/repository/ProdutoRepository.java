package com.loja.loja.repository;

import com.loja.loja.entidades.Categoria;
import com.loja.loja.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
    @Query("SELECT obj FROM Produto obj WHERE obj.descricaoCurta =:desc")
    Produto findBySimpleDescription(@Param("desc") String desc);
}
