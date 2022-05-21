package br.edu.ifsp.domain.usecases.fichaTreino;

import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarFichaTreinoUC {
    private FichaTreinoDAO fichaTreinoDAO;

    public CriarFichaTreinoUC(FichaTreinoDAO fichaTreinoDAO) {
        this.fichaTreinoDAO = fichaTreinoDAO;
    }

    public Integer salvar(FichaTreino fichaTreino) {
        Validator<FichaTreino> validator = new FichaTreinoValidator();
        Notification notification = validator.validar(fichaTreino);

        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

        return fichaTreinoDAO.create(fichaTreino);
    }
}
