package com.loja.loja.controle;

import com.loja.loja.dto.AuthenticationDTO;
import com.loja.loja.dto.LoginResponseDTO;
import com.loja.loja.entidades.Usuario;
import com.loja.loja.repository.UserRepository;
import com.loja.loja.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity auth(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        Usuario usuario = repository.findByLogin(data.login());
        if (usuario != null && new BCryptPasswordEncoder().matches(data.senha(), usuario.getSenha())) {
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha(), usuario.getNivelAcesso(), token));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
