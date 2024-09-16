package com.mensageria.dao;


import com.mensageria.model.Alunos;
import com.mensageria.model.Curso;
import com.mensageria.model.Cursos;

import java.util.List;
import java.util.Optional;

public interface iCursoDAO {
    Curso create(Curso cursos);

    void update(Curso cursos);

    void delete(Alunos cursos);

    Optional<Alunos> findById(Long id);

    List<Alunos> findByArea(Curso cursos);

    List<Alunos> findBySigla(Curso cursos);
}
