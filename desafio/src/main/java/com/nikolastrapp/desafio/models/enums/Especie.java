package com.nikolastrapp.desafio.models.enums;

public enum Especie {

    HOMO_SAPIENS("Homo sapiens"),
    HOMO_SUPERIOR("Homo superior"),
    HOMO_PEREGRINO("Homo peregrino");

    private String especie;

    Especie(String s) {
        this.especie = s;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
}
