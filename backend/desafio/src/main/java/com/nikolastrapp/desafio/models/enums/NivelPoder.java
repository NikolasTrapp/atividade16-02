package com.nikolastrapp.desafio.models.enums;

public enum NivelPoder {
    OMEGA("Omega"),
    EPSILON("Epsilon"),
    DELTA("Delta"),
    SEM_PODER("Sem poder");

    private String nivelPoder;

    NivelPoder(String nivelPoder) {
        this.nivelPoder = nivelPoder;
    }

    public String getNivelPoder() {
        return nivelPoder;
    }

    public void setNivelPoder(String nivelPoder) {
        this.nivelPoder = nivelPoder;
    }
}
