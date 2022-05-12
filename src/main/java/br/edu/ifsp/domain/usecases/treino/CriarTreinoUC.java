package br.edu.ifsp.domain.usecases.treino;

import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarTreinoUC {
    private TreinoDAO treinoDAO;

    public CriarTreinoUC(TreinoDAO treinoDAO) {
        this.treinoDAO = treinoDAO;
    }

    public Integer salvar(Treino treino) {

        Validator<Treino> validator = new TreinoValidator();
        Notification notification = validator.validar(treino);

        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

        Integer id = treino.getId();

        if (treinoDAO.findById(id).isPresent()) {
            throw new EntityAlreadyExistsException("Id já cadastrado");
        }

        return treinoDAO.create(treino);
    }
}
