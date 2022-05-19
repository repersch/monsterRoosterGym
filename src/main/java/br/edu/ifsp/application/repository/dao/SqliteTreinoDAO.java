package br.edu.ifsp.application.repository.dao;

import br.edu.ifsp.application.repository.utils.ConnectionFactory;
import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.usecases.treino.TreinoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteTreinoDAO implements TreinoDAO {
    @Override
    public Integer create(Treino treino) {
        String sql = "INSERT INTO Treino (nome, observacao) VALUES (?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, treino.getNome());
            stmt.setString(2, treino.getObservacao());
            stmt.executeUpdate();
            ResultSet resultado = stmt.getGeneratedKeys();
            return resultado.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            }
        return null;
        }
        @Override
        public Optional<Treino> findByAttribute(String attribute, String key) {
            String sql = "SELECT * FROM Treino WHERE " + attribute + " = ?";
            Treino treino = null;

            try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
                stmt.setString(1, key);
                ResultSet resultado = stmt.executeQuery();
                if (resultado.next()) {
                    treino = resultSetToEntity(resultado);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.ofNullable(treino);
        }

        @Override
        public Optional<Treino> findById (Integer id){
            String sql = "SELECT * FROM Treino WHERE id = ?";
            Treino treino = null;

            try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet resultado = stmt.executeQuery();
                if (resultado.next()) {
                    treino = resultSetToEntity(resultado);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.ofNullable(treino);
        }

        @Override
        public List<Treino> findAll () {
            String sql = "SELECT * FROM Treino";
            List<Treino> treinos = new ArrayList<>();

            try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    Treino treino = resultSetToEntity(resultado);
                    treinos.add(treino);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return treinos;
        }

        @Override
        public boolean update (Treino treino){
            String sql = "UPDATE Treino SET nome = ?, observacao = ? WHERE id = ?";

            try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
                stmt.setString(1, treino.getNome());
                stmt.setString(2, treino.getObservacao());
                stmt.setInt(3, treino.getId());
                stmt.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

    private Treino resultSetToEntity(ResultSet resultado) throws SQLException {
        return new Treino(
                resultado.getInt("id"),
                resultado.getString("nome"),
                resultado.getString("observacao")
        );
    }
}