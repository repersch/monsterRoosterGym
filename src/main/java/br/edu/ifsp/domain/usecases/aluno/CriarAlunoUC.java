package br.edu.ifsp.domain.usecases.aluno;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

public class CriarAlunoUC {

    private AlunoDAO alunoDAO;

    public CriarAlunoUC(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public Integer insert(Aluno aluno) {

        String cpf = aluno.getCpf();

        if (alunoDAO.findByCpf(cpf).isPresent()) {
            throw new EntityAlreadyExistsException("CPF já cadastrado.");
        }

        return alunoDAO.create(aluno);

    }
}
