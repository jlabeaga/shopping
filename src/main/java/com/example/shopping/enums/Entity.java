package com.example.shopping.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;

@AllArgsConstructor
public enum Entity implements Serializable {
    USER("USER"),
    CATEGORY("CATEGORY"),
    PRODUCT("PRODUCT"),
    CART("CART"),
    ORDER("ORDER");

    @Getter
    private final String value;

    public static Entity fromValue(String value) {
        return Arrays.stream(Entity.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
