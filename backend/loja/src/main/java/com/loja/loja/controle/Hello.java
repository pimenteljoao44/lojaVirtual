package com.loja.loja.controle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class Hello {

    @GetMapping("/")
    public String hello() {
        return "Hello World" + new Date();
    }
}
