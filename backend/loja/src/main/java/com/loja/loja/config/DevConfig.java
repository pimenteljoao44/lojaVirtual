package com.loja.loja.config;

import com.loja.loja.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBservice dataBaseService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Bean
    public boolean instanciaDB() {

        if (ddl.equals("create")) {
            this.dataBaseService.instanciaDB();
        }
        return false;
    }
}
