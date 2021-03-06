package br.edu.ifsp.application.repository.dao;

import br.edu.ifsp.application.repository.utils.ConnectionFactory;
import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteUsuarioDAO implements UsuarioDAO {

    @Override
    public Integer create(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, email, senha, isInstrutor, cpf, " +
                "telefone, genero, data_nascimento, peso, altura, observacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {

            setDadosUsuario(usuario, stmt);
            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            return resultado.getInt(1);

            } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<Usuario> findByAttribute(String attribute, String key) {
        String sql = "SELECT * FROM Usuario WHERE " + attribute + " = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, key);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                usuario = getDadosUsuario(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(usuario);
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                usuario = getDadosUsuario(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Usuario usuario = getDadosUsuario(resultado);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public List<Usuario> findAll(String tipoUsuario) {
        String sql = "SELECT * FROM Usuario WHERE isInstrutor = " + ((tipoUsuario.equalsIgnoreCase("aluno")) ? 0 : 1) + "";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Usuario usuario = getDadosUsuario(resultado);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, isInstrutor = ?, cpf = ?, " +
                "telefone = ?, genero = ?, data_nascimento = ?, peso = ?, altura = ?, observacao = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setDadosUsuario(usuario, stmt);

            stmt.setInt(12, usuario.getId());
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Usuario getDadosUsuario(ResultSet resultado) throws SQLException {

        boolean isInstrutor = (resultado.getInt("isInstrutor") == 1) ? true : false;

        Usuario usuario = new Usuario (
            resultado.getInt("id"),
            resultado.getString("nome"),
            resultado.getString("email"),
            resultado.getString("senha"),
            isInstrutor,
            null
        );

        if (!isInstrutor) {
            usuario.setAluno(new Aluno(
                    resultado.getString("cpf"),
                    resultado.getString("telefone"),
                    resultado.getString("genero"),
                    LocalDate.parse(resultado.getString("data_nascimento")),
                    resultado.getDouble("peso"),
                    resultado.getDouble("altura"),
                    resultado.getString("observacao")
            ));
        }

        return usuario;
}

    private void setDadosUsuario(Usuario usuario, PreparedStatement stmt) throws SQLException {

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setInt(4, (usuario.getInstrutor()) ? 1 : 0);
        if (!usuario.getInstrutor()) {
            stmt.setString(5, usuario.getAluno().getCpf());
            stmt.setString(6, usuario.getAluno().getTelefone());
            stmt.setString(7, usuario.getAluno().getGenero());
            stmt.setString(8, usuario.getAluno().getDataNascimento().toString());
            stmt.setDouble(9, usuario.getAluno().getPeso());
            stmt.setDouble(10, usuario.getAluno().getAltura());
            stmt.setString(11, usuario.getAluno().getObservacoes());
        }
    }


}
