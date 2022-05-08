package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.usecases.aluno.AlunoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteAlunoDAO implements AlunoDAO {

    @Override
    public Integer create(Aluno aluno) {
        String sql = "INSERT INTO Aluno (nome, cpf, email, telefone, genero, data_nascimento, peso, altura, observacao)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getGenero());
            stmt.setString(6, aluno.getDataNascimento().toString());
            stmt.setDouble(7, aluno.getPeso());
            stmt.setDouble(8, aluno.getAltura());
            stmt.setString(9, aluno.getObservacoes());

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            return resultado.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Aluno> findOne(Integer id) {
       String sql = "SELECT * FROM Aluno WHERE id = ?";
       Aluno aluno = null;

       try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
           stmt.setInt(1, id);
           ResultSet resultado = stmt.executeQuery();
           if (resultado.next()) {
               aluno = resultSetToEntity(resultado);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

       return Optional.ofNullable(aluno);
    }

    @Override
    public List<Aluno> findAll() {
        String sql = "SELECT * FROM Aluno";
        List<Aluno> alunos = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Aluno aluno = resultSetToEntity(resultado);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    @Override
    public boolean update(Aluno aluno) {
        String sql = "UPDATE Aluno SET nome = ?, cpf = ?, email = ?, telefone = ?, genero = ?, data_nascimento = ?, " +
                "peso = ?, altura = ?, observacao = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getGenero());
            stmt.setString(6, aluno.getDataNascimento().toString());
            stmt.setDouble(7, aluno.getPeso());
            stmt.setDouble(8, aluno.getAltura());
            stmt.setString(9, aluno.getObservacoes());

            stmt.setInt(10, aluno.getId());

            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Aluno> findByCpf(String cpf) {

        String sql = "SELECT * FROM Aluno WHERE cpf = ?";
        Aluno aluno = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                aluno = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(aluno);
    }

    @Override
    public Optional<Aluno> findByNome(String nome) {
        String sql = "SELECT * FROM Aluno WHERE nome = ?";
        Aluno aluno = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                aluno = resultSetToEntity(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(aluno);
    }


    private Aluno resultSetToEntity(ResultSet resultado) throws SQLException {
        return new Aluno(
                resultado.getInt("id"),
                resultado.getString("nome"),
                resultado.getString("cpf"),
                resultado.getString("email"),
                resultado.getString("telefone"),
                resultado.getString("genero"),
                LocalDate.parse(resultado.getString("data_nascimento")),
                resultado.getDouble("peso"),
                resultado.getDouble("altura"),
                resultado.getString("observacao")
                // falta colocar o ultimo treino realizado aqui
        );
    }
}
