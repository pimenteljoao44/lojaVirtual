package com.loja.loja.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/estado").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/estado").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/estado/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/estado/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cidade").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/cidade").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/cidade/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/cidade/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/pessoas").permitAll()
                        .requestMatchers(HttpMethod.POST,"/pessoas").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/pessoas/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/pessoas/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/categorias").permitAll()
                        .requestMatchers(HttpMethod.POST,"/categorias").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/categorias/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/categorias/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/produtos").permitAll()
                        .requestMatchers(HttpMethod.POST,"/produtos").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/produtos/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/produtos/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/imagemProduto").permitAll()
                        .requestMatchers(HttpMethod.POST,"/imagemProduto").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/imagemProduto/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/imagemProduto/{id}").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
