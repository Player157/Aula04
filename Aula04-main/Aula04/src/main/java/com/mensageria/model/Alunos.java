package com.mensageria.model;

public class Alunos {
    private Long matricula;
    private String nome;
    private String telefone;
    private boolean maioridade;
    private Curso curso;
    private String sexo;

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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Alunos{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", maioridade=" + maioridade +
                ", curso=" + curso.getSigla() +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
