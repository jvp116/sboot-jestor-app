package com.jestor.domain.model.financialrecord;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum Type {

    ENTRADA('E'), SAIDA('S');

    Type(char code) {
        this.code = code;
    }

    private final char code;

    @Converter(autoApply = true)
    public static class Mapeador implements AttributeConverter<Type, String> {

        @Override
        public String convertToDatabaseColumn(Type attribute) {
            return String.valueOf(attribute.getCode());
        }

        @Override
        public Type convertToEntityAttribute(String dbData) {
            if (dbData == null) return null;
            if ("E".equals(dbData)) return ENTRADA;
            if ("S".equals(dbData)) return SAIDA;
            throw new IllegalStateException("Valor inválido: " + dbData);
        }
    }
}