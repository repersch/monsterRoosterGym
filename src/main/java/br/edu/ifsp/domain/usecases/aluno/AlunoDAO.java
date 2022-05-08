package br.edu.ifsp.domain.usecases.aluno;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface AlunoDAO extends DAO<Aluno, Integer> {

    Optional<Object> findByCpf(String cpf);
}
