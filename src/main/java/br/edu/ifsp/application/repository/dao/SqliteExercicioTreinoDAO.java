package br.edu.ifsp.application.repository.dao;

import br.edu.ifsp.application.repository.utils.ConnectionFactory;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.exercicioTreino.ExercicioTreinoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.*;

public class SqliteExercicioTreinoDAO implements ExercicioTreinoDAO {
    @Override
    public Integer create(ExercicioTreino exercicioTreino) {
        String sql = "INSERT INTO ExercicioTreino (serie, carga, repeticao, id_treino, id_exercicio)" +
                "VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, exercicioTreino.getSerie());
            stmt.setDouble(2, exercicioTreino.getCarga());
            stmt.setInt(3, exercicioTreino.getRepeticao());
            stmt.setInt(4, exercicioTreino.getTreino().getId());
            stmt.setInt(5, exercicioTreino.getExercicio().getId());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            return resultado.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<ExercicioTreino> findByAttribute(String attribute, String key) {
        String sql = "SELECT * FROM ExercicioTreino WHERE " + attribute + " = ?";
        ExercicioTreino exercicioTreino = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, key);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                exercicioTreino = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(exercicioTreino);
    }

    @Override
    public Optional<ExercicioTreino> findById(Integer id) {
        String sql = "SELECT * FROM ExercicioTreino WHERE id = ?";
        ExercicioTreino exercicioTreino = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                exercicioTreino = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(exercicioTreino);
    }

    @Override
    public List<ExercicioTreino> findAll() {
        String sql = "SELECT * FROM ExercicioTreino";
        List<ExercicioTreino> exerciciosTreino = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ExercicioTreino exercicioTreino = resultSetToEntity(resultado);
                exerciciosTreino.add(exercicioTreino);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exerciciosTreino;
    }

    @Override
    public boolean update(ExercicioTreino exercicioTreino) {
        String sql = "UPDATE ExercicioTreino SET serie = ?, carga = ?, repeticao = ?, id_treino = ?, id_exercicio = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, exercicioTreino.getSerie());
            stmt.setDouble(2, exercicioTreino.getCarga());
            stmt.setInt(3, exercicioTreino.getRepeticao());
            stmt.setInt(4, exercicioTreino.getTreino().getId());
            stmt.setInt(5, exercicioTreino.getExercicio().getId());

            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private ExercicioTreino resultSetToEntity(ResultSet resultado) throws SQLException {
        Integer id_treino = resultado.getInt("id_treino");
        Treino treino = (Treino) buscarTreinoUC.buscarPorId(id_treino).get();

        Integer id_exercicio = resultado.getInt("id_exercicio");
        Exercicio exercicio = (Exercicio) buscarExercicioUC.buscarPorId(id_exercicio).get();

        return new ExercicioTreino(
                resultado.getInt("id"),
                resultado.getInt("serie"),
                resultado.getDouble("carga"),
                resultado.getInt("repeticao"),
                treino,
                exercicio
        );
    }
}
