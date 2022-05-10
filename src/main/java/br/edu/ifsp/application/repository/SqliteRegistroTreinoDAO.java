package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.RegistroTreino;
import br.edu.ifsp.domain.usecases.registroTreino.RegistroTreinoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SqliteRegistroTreinoDAO implements RegistroTreinoDAO {


    @Override
    public Integer create(RegistroTreino registroTreino) {

        String sql = "INSERT INTO Registro_Treino (inicio, fim, finalizado, aluno) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, registroTreino.getInicio().toString());
            stmt.setString(2, registroTreino.getFim().toString());
            // se esta criando o treino significa que não esta finalizado, por isso é 0 (false)
            stmt.setInt(3, 0);
            stmt.setInt(4, registroTreino.getAluno().getId());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            int chaveGerada = resultado.getInt(1);

            return chaveGerada;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<RegistroTreino> findByAttribute(String attribute, String key) {
        return Optional.empty();
    }

    @Override
    public Optional<RegistroTreino> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<RegistroTreino> findAll() {
        return null;
    }

    @Override
    public boolean update(RegistroTreino registroTreino) {
        return false;
    }
}
