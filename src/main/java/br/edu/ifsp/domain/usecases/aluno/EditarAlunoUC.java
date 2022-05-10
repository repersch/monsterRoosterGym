package br.edu.ifsp.domain.usecases.aluno;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class EditarAlunoUC {

    private AlunoDAO alunoDAO;

    public EditarAlunoUC(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public boolean atualizar(Aluno aluno) {

        Validator<Aluno> validator = new AlunoValidator();
        Notification notificacao = validator.validar(aluno);

        if (notificacao.possuiErros()) {
           throw  new IllegalArgumentException(notificacao.mensagemDeErro());
        }

        Integer id = aluno.getId();

        if (alunoDAO.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Aluno não encontrado.");
        }

        return alunoDAO.update(aluno);
    }
}
