package com.mensageria.dao;

import com.mensageria.config.ConnectionFactory;
import com.mensageria.model.Alunos;
import com.mensageria.model.Cursos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO implements IAlunoDAO {

    @Override
    public Alunos create(Alunos aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT into alunos " +
                    "(nome, telefone, maioridade, curso, sexo)" +
                    "values (?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getTelefone());
            preparedStatement.setBoolean(3, aluno.isMaioridade());
            preparedStatement.setString(4, aluno.getCurso());
            preparedStatement.setString(5, aluno.getSexo());
            preparedStatement.executeUpdate();

            // recuperando o ID
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                aluno.setMatricula(resultSet.getLong("matricula"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setTelefone(resultSet.getString("telefone"));
                aluno.setMaioridade(resultSet.getBoolean("maioridade"));
                aluno.setCurso(resultSet.getString("curso"));
                aluno.setSexo(resultSet.getString("sexo"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aluno;
    }

    @Override
    public void update(Alunos aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE alunos SET " +
                    "nome = ?, telefone = ?, maioridade = ?, curso = ?, sexo = ? " +
                    "WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getTelefone());
            preparedStatement.setBoolean(3, aluno.isMaioridade());
            preparedStatement.setString(4, aluno.getCurso());
            preparedStatement.setString(5, aluno.getSexo());
            preparedStatement.setLong(6, aluno.getMatricula());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Alunos aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM alunos " +
                    "WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, aluno.getMatricula());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Alunos> findById(Long id) {
        Alunos aluno = new Alunos();
        String query = "SELECT * FROM alunos WHERE matricula = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            aluno.setMatricula(resultSet.getLong("matricula"));
            aluno.setNome(resultSet.getString("nome"));
            aluno.setTelefone(resultSet.getString("telefone"));
            aluno.setMaioridade(resultSet.getBoolean("maioridade"));
            aluno.setCurso(resultSet.getString("curso"));
            aluno.setSexo(resultSet.getString("sexo"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(aluno);
    }

    @Override
    public List<Alunos> findAll() {
        List<Alunos> alunos = new ArrayList<>();
        String query = "SELECT * FROM alunos";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Alunos aluno = new Alunos();
                aluno.setMatricula(resultSet.getLong("matricula"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setTelefone(resultSet.getString("telefone"));
                aluno.setMaioridade(resultSet.getBoolean("maioridade"));
                aluno.setCurso(resultSet.getString("curso"));
                aluno.setSexo(resultSet.getString("sexo"));
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alunos;
    }

    @Override
    public List<Alunos> findByCurso(Cursos curso) {
        List<Alunos> alunos = new ArrayList<>();
        String query = "SELECT * FROM alunos WHERE curso = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(curso));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Alunos aluno = new Alunos();
                aluno.setMatricula(resultSet.getLong("matricula"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setTelefone(resultSet.getString("telefone"));
                aluno.setMaioridade(resultSet.getBoolean("maioridade"));
                aluno.setCurso(resultSet.getString("curso"));
                aluno.setSexo(resultSet.getString("sexo"));
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alunos;
    }
}