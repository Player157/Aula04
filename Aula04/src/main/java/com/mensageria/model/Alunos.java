package com.mensageria.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alunos {
    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isMaioridade() {
        return maioridade;
    }

    public void setMaioridade(boolean maioridade) {
        this.maioridade = maioridade;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Alunos(Long matricula, String nome, String telefone, boolean maioridade, Cursos curso, String sexo) {
        this.matricula = matricula;
        this.nome = nome;
        this.telefone = telefone;
        this.maioridade = maioridade;
        this.curso = curso;
        this.sexo = sexo;
    }

    Long matricula;
    String nome;
    String telefone;
    boolean maioridade;
    Cursos curso;
    String sexo;

}