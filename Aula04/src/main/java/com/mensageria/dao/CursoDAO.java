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
            String query = "INSERT into cursos " +
                    "(codigo, nome, sigla, area)" +
                    "values (?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, cursos.getCodigo());
            preparedStatement.setString(2, cursos.getNome());
            preparedStatement.setString(3, cursos.getSigla());
            preparedStatement.setString(4, cursos.getArea());
            preparedStatement.executeUpdate();

            // recuperando o ID
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                cursos.setCodigo(resultSet.getLong("codigo"));
                cursos.setNome(resultSet.getString("nome"));
                cursos.setSigla(resultSet.getString("Sigla"));
                cursos.setArea(Areas.valueOf(resultSet.getString("Area")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cursos;
    }

    public void update(Curso cursos) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE cursos SET " +
                    "nome = ?, sigla = ?, area = ?" +
                    "WHERE codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, cursos.getCodigo());
            preparedStatement.setString(2, cursos.getNome());
            preparedStatement.setString(3, cursos.getSigla());
            preparedStatement.setString(4, cursos.getArea());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Curso cursos) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM cursos " +
                    "WHERE codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, cursos.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Curso> findById(int codigo) {
        Curso cursos = new Curso();
        String query = "SELECT * FROM curso WHERE codigo = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cursos.setCodigo(resultSet.getLong("codigo"));
            cursos.setNome(resultSet.getString("nome"));
            cursos.setSigla(resultSet.getString("Sigla"));
            cursos.setArea(Areas.valueOf(resultSet.getString("Area")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(cursos);
    }


}
