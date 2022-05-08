package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.Instrutor;
import br.edu.ifsp.domain.usecases.instrutor.InstrutorDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SqliteInstrutorDAO implements InstrutorDAO {


    @Override
    public Integer create(Instrutor instrutor) {
        String sql = "INSERT INTO Instrutor(nome) VALUES (?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, instrutor.getNome());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            return resultado.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<Instrutor> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Instrutor> findAll() {
        return null;
    }

    @Override
    public boolean update(Instrutor type) {
        return false;
    }

    @Override
    public Optional<Instrutor> findByNome(String nome) {
        String sql = "SELECT * FROM Instrutor WHERE nome = ?";
        Instrutor instrutor = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, nome);

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                instrutor = new Instrutor(resultado.getInt("id"),
                                                    resultado.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(instrutor);
    }
}
