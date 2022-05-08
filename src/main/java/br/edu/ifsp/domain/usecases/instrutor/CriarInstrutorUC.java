package br.edu.ifsp.domain.usecases.instrutor;

import br.edu.ifsp.domain.entities.Instrutor;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarInstrutorUC {

    private InstrutorDAO instrutorDAO;

    public CriarInstrutorUC(InstrutorDAO instrutorDAO) {
        this.instrutorDAO = instrutorDAO;
    }

    public Integer salvar(Instrutor instrutor) {

        Validator<Instrutor> validator = new InstrutorValidator();
        Notification notification = validator.validar(instrutor);

        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

        String nome = instrutor.getNome();

        if (instrutorDAO.findByNome(nome).isPresent()) {
            throw new EntityAlreadyExistsException("Nome já cadastrado.");
        }

        return instrutorDAO.create(instrutor);
    }
}
