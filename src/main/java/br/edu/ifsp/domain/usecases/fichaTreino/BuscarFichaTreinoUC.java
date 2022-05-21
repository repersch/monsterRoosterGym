package br.edu.ifsp.domain.usecases.fichaTreino;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class BuscarFichaTreinoUC {
    private FichaTreinoDAO fichaTreinoDAO;

    public BuscarFichaTreinoUC(FichaTreinoDAO fichaTreinoDAO) {
        this.fichaTreinoDAO = fichaTreinoDAO;
    }

    public Optional<FichaTreino> buscarPorId(Integer id) {
        if (id == null) {
            throw  new IllegalArgumentException("Id não pode ser nulo");
        }
        return fichaTreinoDAO.findById(id);
    }

    public List<FichaTreino> buscarPorAluno (Usuario aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo.");
        }
        return fichaTreinoDAO.findByAluno(aluno);
    }

    public List<FichaTreino> buscarTodos() {
        return fichaTreinoDAO.findAll();
    }
}
