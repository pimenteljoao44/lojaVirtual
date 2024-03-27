package com.loja.loja.service;

import com.loja.loja.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DBservice {
    @Autowired
    private EstadoRepository funcionarioRepository;

    @Transactional
    public void instanciaDB() {

    }

}
