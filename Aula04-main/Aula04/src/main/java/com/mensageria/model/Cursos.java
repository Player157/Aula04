package com.mensageria.model;

public enum Cursos {
    ADS("ADS"),
    CCMP("CCMP"),
    ECMP("ECMP"),
    OUTROS("OUTROS");

    private final String sigla;

    // Construtor da enum
    Cursos(String sigla) {
        this.sigla = sigla;
    }

    // Método para obter a sigla
    public String getSigla() {
        return sigla;
    }
}
