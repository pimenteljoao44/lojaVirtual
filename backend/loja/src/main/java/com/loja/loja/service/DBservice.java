package com.loja.loja.service;

import com.loja.loja.entidades.Estado;
import com.loja.loja.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DBservice {
    @Autowired
    private EstadoRepository funcionarioRepository;

    public void instanciaDB() {
        Estado e1 = new Estado(1, "Paraná", "PR", new Date(), new Date());

        funcionarioRepository.saveAll(List.of(e1));
    }

}
