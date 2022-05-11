package br.edu.ifsp.application.repository.dao;

import br.edu.ifsp.application.repository.utils.ConnectionFactory;
import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.entities.GrupoMuscular;
import br.edu.ifsp.domain.usecases.exercicio.ExercicioDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteExercicioDAO implements ExercicioDAO {
    @Override
    public Integer create(Exercicio exercicio) {
        String sql = "INSERT INTO Exercicio (nome, grupo_muscular, descricao, em_uso)" +
                "VALUES (?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getGrupoMuscular().toString());
            stmt.setString(3, exercicio.getDescricao());
            stmt.setBoolean(4, exercicio.getEmUso());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            return resultado.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Exercicio> findByAttribute(String attribute, String key) {
        String sql = "SELECT * FROM Exercicio WHERE " + attribute + " = ?";
        Exercicio exercicio = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, key);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                exercicio = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(exercicio);
    }

    @Override
    public Optional<Exercicio> findById(Integer id) {
        String sql = "SELECT * FROM Exercicio WHERE id = ?";
        Exercicio exercicio = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                exercicio = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(exercicio);
    }

    @Override
    public List<Exercicio> findAll() {
        String sql = "SELECT * FROM Exercicio";
        List<Exercicio> exercicios = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Exercicio exercicio = resultSetToEntity(resultado);
                exercicios.add(exercicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercicios;
    }

    @Override
    public boolean update(Exercicio exercicio) {
        String sql = "UPDATE Exercicio SET nome = ?, grupo_muscular = ?, descricao = ?, em_uso = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getGrupoMuscular().toString());
            stmt.setString(3, exercicio.getDescricao());
            stmt.setBoolean(4, exercicio.getEmUso());

            stmt.setInt(5, exercicio.getId());

            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM Exercicio WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Exercicio exercicio) {
        if (exercicio == null || exercicio.getId() == null) {
            throw new IllegalArgumentException("Exercício e exercício ID não podem ser nulos.");
        }
        return deleteById(exercicio.getId());
    }

    private Exercicio resultSetToEntity(ResultSet resultado) throws SQLException {
        return new Exercicio(
                resultado.getInt("id"),
                resultado.getString("nome"),
                GrupoMuscular.toEnum(resultado.getString("grupo_muscular")),
                resultado.getString("descricao"),
                resultado.getBoolean("em_uso")
        );
    }
}
