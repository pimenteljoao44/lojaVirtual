package com.loja.loja.config;

import com.loja.loja.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBservice dataBaseService;

    @Bean
    public void instanciaDB() {

    }
}
