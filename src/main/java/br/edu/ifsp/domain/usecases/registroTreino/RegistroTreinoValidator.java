package br.edu.ifsp.domain.usecases.registroTreino;

import br.edu.ifsp.domain.entities.RegistroTreino;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class RegistroTreinoValidator extends Validator<RegistroTreino> {


    @Override
    public Notification validar(RegistroTreino registroTreino) {

        Notification notification = new Notification();

        if (registroTreino == null) {
            notification.addErro("Registro de Treino é nulo.");
            return notification;
        }
        if (nuloOuVazio(registroTreino.getInicio().toString())) {
            notification.addErro("Registro de Treino é nulo ou vazio.");
            return notification;
        }
        return notification;
    }
}

