package com.jestor.domain.model;

import lombok.Getter;

@Getter
public enum Tipo {

    E('E', "Entrada"), S('S', "Saída");

    Tipo(char codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private final char codigo;
    private final String descricao;
}
