package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.Instrutor;
import br.edu.ifsp.domain.usecases.instrutor.InstrutorDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteInstrutorDAO implements InstrutorDAO {


    @Override
    public Integer create(Instrutor instrutor) {
        String sql = "INSERT INTO Instrutor(nome, email, senha) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getEmail());
            stmt.setString(3, instrutor.getSenha());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            return resultado.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<Instrutor> findByAttribute(String attribute, String key) {
        String sql = "SELECT * FROM Instrutor WHERE " + attribute + " = ?";
        Instrutor instrutor = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, key);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                instrutor = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(instrutor);
    }

    @Override
    public Optional<Instrutor> findById(Integer id) {
        String sql = "SELECT * FROM Instrutor WHERE id = ?";
        Instrutor instrutor = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                instrutor = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(instrutor);
    }

    @Override
    public List<Instrutor> findAll() {
        String sql = "SELECT * FROM Instrutor";
        List<Instrutor> instrutores = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Instrutor instrutor = resultSetToEntity(resultado);
                instrutores.add(instrutor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instrutores;
    }


    @Override
    public boolean update(Instrutor type) {
        return false;
    }

    private Instrutor resultSetToEntity(ResultSet resultado) throws SQLException {

        return new Instrutor(
                resultado.getInt("id"),
                resultado.getString("nome"),
                resultado.getString("email"),
                resultado.getString("senha")
        );

    }
}
