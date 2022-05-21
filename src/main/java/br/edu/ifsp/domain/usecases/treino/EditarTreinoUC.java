package br.edu.ifsp.domain.usecases.treino;

import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class EditarTreinoUC {
    private TreinoDAO treinoDAO;

    public EditarTreinoUC(TreinoDAO treinoDAO) {
        this.treinoDAO = treinoDAO;
    }

    public boolean editar(Treino treino) {

        Validator<Treino> validator = new TreinoValidator();
        Notification notification = validator.validar(treino);

        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

        Integer id = treino.getId();

        if (treinoDAO.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Id não encontrado.");
        }

        return treinoDAO.update(treino);
    }
}
