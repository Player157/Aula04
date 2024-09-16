package com.mensageria.dao;

import com.mensageria.config.ConnectionFactory;
import com.mensageria.model.Alunos;
import com.mensageria.model.Areas;
import com.mensageria.model.Curso;
import com.mensageria.model.Cursos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO {
    public Curso create(Curso cursos) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO cursos " +
                    "(nome, sigla, area) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cursos.getNome());
            preparedStatement.setString(2, cursos.getSigla());
            preparedStatement.setString(3, cursos.getArea().name());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                cursos.setCodigo(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cursos;
    }

    public void update(Curso cursos) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE cursos SET " +
                    "nome = ?, sigla = ?, area = ? " +
                    "WHERE codigo = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cursos.getNome());
            preparedStatement.setString(2, cursos.getSigla());
            preparedStatement.setString(3, cursos.getArea().name());
            preparedStatement.setLong(4, cursos.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Curso cursos) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM cursos WHERE codigo = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, cursos.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Curso> findById(Long codigo) {
        Curso cursos = new Curso();
        String query = "SELECT * FROM cursos WHERE codigo = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cursos.setCodigo(resultSet.getLong("codigo"));
                cursos.setNome(resultSet.getString("nome"));
                cursos.setSigla(resultSet.getString("sigla"));
                cursos.setArea(Areas.valueOf(resultSet.getString("area")));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(cursos);
    }

    public Optional<Curso> findByArea(Areas area) {
        Curso cursos = new Curso();
        String query = "SELECT * FROM curso WHERE area = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, area.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cursos.setCodigo(resultSet.getLong("codigo"));
                cursos.setNome(resultSet.getString("nome"));
                cursos.setSigla(resultSet.getString("sigla"));
                cursos.setArea(Areas.valueOf(resultSet.getString("area")));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(cursos);
    }

    public Optional<Curso> findBySigla(String sigla) {
        Curso curso = new Curso();
        String query = "SELECT * FROM cursos WHERE sigla = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sigla);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                curso.setCodigo(resultSet.getLong("codigo"));
                curso.setNome(resultSet.getString("nome"));
                curso.setSigla(resultSet.getString("sigla"));
                curso.setArea(Areas.valueOf(resultSet.getString("area")));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(curso);
    }


}
