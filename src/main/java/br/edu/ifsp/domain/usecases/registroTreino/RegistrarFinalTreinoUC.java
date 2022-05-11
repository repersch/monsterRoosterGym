package br.edu.ifsp.domain.usecases.registroTreino;

import br.edu.ifsp.domain.entities.EstadoRegistroTreino;
import br.edu.ifsp.domain.entities.RegistroTreino;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class RegistrarFinalTreinoUC {

    private final RegistroTreinoDAO registroTreinoDAO;

    public RegistrarFinalTreinoUC(RegistroTreinoDAO registroTreinoDAO) {
        this.registroTreinoDAO = registroTreinoDAO;
    }

    public boolean finalizarTreino(RegistroTreino registroTreino) {

        Validator<RegistroTreino> validator = new RegistroTreinoValidator();
        Notification notification = validator.validar(registroTreino);

        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

        registroTreino.setEstadoRegistroTreino(EstadoRegistroTreino.FINALIZADO);
        return registroTreinoDAO.update(registroTreino);
    }


}
