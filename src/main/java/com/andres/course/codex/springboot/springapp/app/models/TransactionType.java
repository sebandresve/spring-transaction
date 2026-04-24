package com.andres.course.codex.springboot.springapp.app.models;

public enum TransactionType {

    INCOME("Ingreso"),
    EXPRENSE("Egreso");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
