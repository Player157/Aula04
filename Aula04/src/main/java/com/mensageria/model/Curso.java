package com.mensageria.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    public Curso() {

    }

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


    public void setArea(Areas area) {
        this.area = area;
    }


    public Curso(Long codigo, String nome, String sigla, Areas area) {
        this.codigo = codigo;
        this.nome = nome;
        this.sigla = sigla;
        this.area = area;
    }
    Long codigo;
    String nome;
    String sigla;
    Areas area;

    public String getArea() {
        return null;
    }
}
