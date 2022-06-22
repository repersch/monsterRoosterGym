package br.edu.ifsp.domain.usecases.registroTreino;

import br.edu.ifsp.domain.entities.EstadoRegistroTreino;
import br.edu.ifsp.domain.entities.RegistroTreino;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.List;

public class RegistrarInicioTreinoUC {

    private final RegistroTreinoDAO registroTreinoDAO;

    public RegistrarInicioTreinoUC(RegistroTreinoDAO registroTreinoDAO) {
        this.registroTreinoDAO = registroTreinoDAO;
    }

    public RegistroTreino iniciarTreino(RegistroTreino registroTreino) {

        Validator<RegistroTreino> validator = new RegistroTreinoValidator();
        Notification notification = validator.validar(registroTreino);

        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

        registroTreino.setEstadoRegistroTreino(EstadoRegistroTreino.INICIADO);
        Integer id = registroTreinoDAO.create(registroTreino);

        return registroTreinoDAO.findById(id).get();
    }
}