package br.edu.ifsp.application.repository.dao;

import br.edu.ifsp.application.repository.utils.ConnectionFactory;
import br.edu.ifsp.domain.entities.EstadoRegistroTreino;
import br.edu.ifsp.domain.entities.RegistroTreino;
import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.registroTreino.RegistroTreinoDAO;
import br.edu.ifsp.domain.usecases.treino.BuscarTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteRegistroTreinoDAO implements RegistroTreinoDAO {

    private BuscarTreinoUC buscarTreinoUC;
    private BuscarUsuarioUC buscarUsuarioUC;

    @Override
    public Integer create(RegistroTreino registroTreino) {

        String sql = "INSERT INTO Registro_Treino (inicio, fim, estado, id_aluno, id_treino) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, registroTreino.getInicio().toString());
            stmt.setString(2, registroTreino.getFim().toString());
            stmt.setString(3, registroTreino.getEstadoRegistroTreino().name());
            stmt.setInt(4, registroTreino.getUsuario().getId());
            stmt.setInt(5, registroTreino.getTreino().getId());

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
    public List<RegistroTreino> findByAttribute(String attribute, Integer key) {

        String sql = "SELECT * FROM registro_treino WHERE " + attribute + " = ?";
        List<RegistroTreino> registrosTreino = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                registrosTreino.add(resultSetToEntity(resultado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrosTreino;
    }

    @Override
    public Optional<RegistroTreino> findById(Integer id) {

        String sql = "SELECT * FROM registro_treino WHERE id = ?";
        RegistroTreino registroTreino = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                registroTreino = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(registroTreino);
    }


    @Override
    public List<RegistroTreino> findAll() {

        String sql = "SELECT * FROM registro_treino";
        List<RegistroTreino> registros = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                registros.add(resultSetToEntity(resultado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public List<RegistroTreino> findByIdAluno(Integer id_aluno) {
        return findByAttribute("id_aluno", id_aluno);
    }

    public List<RegistroTreino> findByIdTreino(Integer id_treino) {
        return findByAttribute("id_treino", id_treino);
    }

    @Override
    public boolean update(RegistroTreino registroTreino) {

        String sql = "UPDATE Registro_Treino SET estado = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {

            stmt.setString(1, registroTreino.getEstadoRegistroTreino().toString());
            stmt.setInt(2, registroTreino.getId());
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private RegistroTreino resultSetToEntity(ResultSet resultado) throws SQLException {
        buscarTreinoUC = new BuscarTreinoUC(new SqliteTreinoDAO());
        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());

        Usuario aluno = buscarUsuarioUC.buscarPorId(resultado.getInt("id_aluno")).get();
        Treino treino = buscarTreinoUC.buscarPorId(resultado.getInt("id_treino")).get();

        return new RegistroTreino(resultado.getInt("id"),
                LocalDateTime.parse(resultado.getString("inicio")),
                LocalDateTime.parse(resultado.getString("fim")),
                EstadoRegistroTreino.toEnum(resultado.getString("estado")),
                aluno,
                treino
        );
    }

}

