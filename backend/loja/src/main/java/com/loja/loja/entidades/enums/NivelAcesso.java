package com.loja.loja.entidades.enums;


public enum NivelAcesso {

    USER(0, "USER"),
    ADMIN(1, "ADMIN");

    private Integer cod;
    private String descricao;

    private NivelAcesso(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static NivelAcesso toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (NivelAcesso nivel : NivelAcesso.values()) {
            if (cod.equals(nivel.getCod())) {
                return nivel;
            }
        }

        throw new IllegalArgumentException("Nível de acesso inválido! " + cod);
    }
}