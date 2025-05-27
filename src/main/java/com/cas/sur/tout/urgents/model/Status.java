package com.cas.sur.tout.urgents.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    @JsonEnumDefaultValue
    EN_COURS("en cours"),
    ANNULE("annule"),
    TRAITE("traiter");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }
}
