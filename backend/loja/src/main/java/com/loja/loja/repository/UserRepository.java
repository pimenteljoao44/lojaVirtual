package com.loja.loja.repository;

import com.loja.loja.entidades.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT obj FROM Usuario obj WHERE obj.login =:login")
    Usuario findByLogin(@Param("login") String login);
}
