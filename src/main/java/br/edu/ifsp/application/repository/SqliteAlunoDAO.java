package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.usecases.aluno.AlunoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            int idGerado = resultado.getInt(1);
            return idGerado;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Aluno> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Aluno> findAll() {
        return null;
    }

    @Override
    public boolean update(Aluno type) {
        return false;
    }
}
