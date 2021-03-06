package br.edu.ifsp.application.repository.dao;

import br.edu.ifsp.application.repository.utils.ConnectionFactory;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.fichaTreino.FichaTreinoDAO;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.buscarUsuarioUC;

public class SqliteFichaTreinoDAO implements FichaTreinoDAO {

    private final BuscarUsuarioUC buscarUsuarioUC;
    public SqliteFichaTreinoDAO() {
        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
    }

    @Override
    public Integer create(FichaTreino fichaTreino) {
        String sql = "INSERT INTO FichaTreino (valido, dataInicio, validade, id_aluno, id_instrutor)" +
                "VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setBoolean(1, fichaTreino.getValido());
            stmt.setString(2, fichaTreino.getDataInicio().toString());
            stmt.setString(3, fichaTreino.getValidade().toString());
            stmt.setInt(4, fichaTreino.getAluno().getId());
            stmt.setInt(5, fichaTreino.getInstrutor().getId());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            return resultado.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<FichaTreino> findByAttribute(String attribute, String key) {
        String sql = "SELECT * FROM FichaTreino WHERE " + attribute + " = ?";
        FichaTreino fichaTreino = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, key);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                fichaTreino = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(fichaTreino);
    }

    @Override
    public Optional<FichaTreino> findById(Integer id) {
        String sql = "SELECT * FROM FichaTreino WHERE id = ?";
        FichaTreino fichaTreino = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                fichaTreino = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(fichaTreino);
    }

    @Override
    public List<FichaTreino> findAll() {
        String sql = "SELECT * FROM FichaTreino";
        List<FichaTreino> fichasTreino = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                FichaTreino fichaTreino = resultSetToEntity(resultado);
                fichasTreino.add(fichaTreino);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fichasTreino;
    }

    @Override
    public boolean update(FichaTreino fichaTreino) {
        String sql = "UPDATE FichaTreino SET valido = ?, dataInicio = ?, validade = ?, id_aluno = ?, id_instrutor = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setBoolean(1, fichaTreino.getValido());
            stmt.setString(2, fichaTreino.getDataInicio().toString());
            stmt.setString(3, fichaTreino.getValidade().toString());
            stmt.setInt(4, fichaTreino.getAluno().getId());
            stmt.setInt(5, fichaTreino.getInstrutor().getId());
            stmt.setInt(6, fichaTreino.getId());

            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private FichaTreino resultSetToEntity(ResultSet resultado) throws SQLException {

        Integer id_instrutor = resultado.getInt("id_instrutor"); //id instrutor e id aluno
        Usuario instrutor = buscarUsuarioUC.buscarPorId(id_instrutor).get();

        Integer id_aluno = resultado.getInt("id_aluno");
        Usuario aluno = buscarUsuarioUC.buscarPorId(id_aluno).get();

        return new FichaTreino(
                resultado.getInt("id"),
                resultado.getBoolean("valido"),
                LocalDate.parse(resultado.getString("dataInicio")),
                LocalDate.parse(resultado.getString("validade")),
                aluno,
                instrutor
        );
    }

    @Override
    public List<FichaTreino> findByAluno (Usuario aluno) {
        String sql = "SELECT * FROM FichaTreino WHERE id_aluno = ?";
        List<FichaTreino> fichasTreino = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, aluno.getId());
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                FichaTreino fichaTreino = resultSetToEntity(resultado);
                fichasTreino.add(fichaTreino);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fichasTreino;
    }
}
