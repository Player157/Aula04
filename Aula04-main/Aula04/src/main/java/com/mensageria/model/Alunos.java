package com.mensageria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Alunos {
    private Long matricula;
    private String nome;
    private String telefone;
    private boolean maioridade;
    private Curso curso;
    private String sexo;

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
