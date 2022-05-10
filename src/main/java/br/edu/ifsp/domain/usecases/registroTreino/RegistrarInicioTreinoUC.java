package br.edu.ifsp.domain.usecases.registroTreino;

import br.edu.ifsp.domain.entities.RegistroTreino;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class RegistrarInicioTreinoUC {

    private final RegistroTreinoDAO registroTreinoDAO;

    public RegistrarInicioTreinoUC(RegistroTreinoDAO registroTreinoDAO) {
        this.registroTreinoDAO = registroTreinoDAO;
    }

    public Integer salvar(RegistroTreino registroTreino) {

        Validator<RegistroTreino> validator = new RegistroTreinoValidator();
        Notification notification = validator.validar(registroTreino);

        return registroTreinoDAO.create(registroTreino);

    }
}