package br.edu.ifsp.domain.usecases.registroTreino;

import br.edu.ifsp.domain.entities.RegistroTreino;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.List;

public interface RegistroTreinoDAO extends DAO<RegistroTreino, Integer> {

    List<RegistroTreino> findByIdAluno(Integer id_aluno);

}
