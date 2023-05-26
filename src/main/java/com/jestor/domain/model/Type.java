package com.jestor.domain.model;

import lombok.Getter;

@Getter
public enum Type {

	E('E', "Entrada"), S('S', "Saída");

	Type(char code, String description) {
		this.code = code;
		this.description = description;
	}

	private final char code;
	private final String description;
}
