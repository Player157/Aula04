package com.mensageria;

import com.mensageria.dao.AlunoDAO;
import com.mensageria.model.Alunos;
import com.mensageria.model.Cursos;
import com.mensageria.util.Funcoes;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        AlunoDAO dao = new AlunoDAO();
        Alunos aluno = new Alunos();
        /*
        aluno.setNome("Socorro de Deus");
        aluno.setSexo("feminino");
        aluno.setMaioridade(true);
        aluno.setTelefone("2345678");
        aluno.setCurso(Cursos.ADS);


        AlunoDAO dao = new AlunoDAO();
        dao.create(aluno);

        aluno.setNome("Amanda");
        aluno.setSexo("feminino");
        aluno.setMaioridade(true);
        aluno.setTelefone("555666777");
        aluno.setCurso(Cursos.ECMP);
        dao.create(aluno);
        */

        Optional<Alunos> a = dao.findById(3l);

        Funcoes.print(a.get());

        List<Alunos> alunos = dao.findAll();

        Funcoes.printList(alunos);
        /*
        aluno.setNome("Roberto Parametro");
        aluno.setSexo("masculino");
        aluno.setMaioridade(false);
        aluno.setTelefone("33344456");
        aluno.setCurso(Cursos.OUTROS);
        dao.create(aluno);

        Funcoes.print(aluno);
        */
        System.err.println("=== Find By Curso ===");
        alunos = dao.findByCurso(Cursos.OUTROS);
        Funcoes.printList(alunos);

    }
}