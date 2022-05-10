package br.edu.ifsp.domain.usecases.aluno;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarAlunoUC {

    private AlunoDAO alunoDAO;


    public CriarAlunoUC(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public Integer salvar(Aluno aluno) {

        Validator<Aluno> validator = new AlunoValidator();
        Notification notification = validator.validar(aluno);

        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

        String cpf = aluno.getCpf();

        if (alunoDAO.findByAttribute("cpf", cpf).isPresent()) {
            throw new EntityAlreadyExistsException("CPF já cadastrado.");
        }

        return alunoDAO.create(aluno);

    }
}
