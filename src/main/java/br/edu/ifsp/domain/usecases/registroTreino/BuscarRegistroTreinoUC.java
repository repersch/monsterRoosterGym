package br.edu.ifsp.domain.usecases.registroTreino;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.RegistroTreino;

import java.util.List;

public class BuscarRegistroTreinoUC {

    private final RegistroTreinoDAO registroTreinoDAO;

    public BuscarRegistroTreinoUC(RegistroTreinoDAO registroTreinoDAO) {
        this.registroTreinoDAO = registroTreinoDAO;
    }

    public RegistroTreino buscarPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo.");
        }

        return registroTreinoDAO.findById(id).get();
    }

    public List<RegistroTreino> buscarPorAluno(Integer id_aluno) {
        if (id_aluno == null) {
            throw new IllegalArgumentException("Id não pode ser nulo.");
        }

        return registroTreinoDAO.findByIdAluno(id_aluno);
    }

    public List<RegistroTreino> buscarPorAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo.");
        }
        return buscarPorAluno(aluno.getId());
    }

    public List<RegistroTreino> buscarTodos() {
        return registroTreinoDAO.findAll();
    }

}
