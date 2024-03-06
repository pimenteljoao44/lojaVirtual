package com.loja.loja.dto;

import com.loja.loja.entidades.enums.NivelAcesso;

public record LoginResponseDTO(Integer id, String nome, String login, String senha, NivelAcesso nivelAcesso,
                               String token) {
}
