package com.mensageria.model;

public class Curso {
    private Long codigo;
    private String nome;
    private String sigla;
    private Areas area; // Assumindo que Areas é um enum ou classe que já foi definido.

    // Construtores
    public Curso() {}

    public Curso(String sigla) {
        this.sigla = sigla;
    }

    public Curso(Long codigo, String nome, String sigla, Areas area) {
        this.codigo = codigo;
        this.nome = nome;
        this.sigla = sigla;
        this.area = area;
    }

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }

    // Métodos adicionais, como toString, hashCode, equals, etc., podem ser adicionados aqui.
}
