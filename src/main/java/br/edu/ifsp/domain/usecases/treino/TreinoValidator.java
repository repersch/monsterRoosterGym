package br.edu.ifsp.domain.usecases.treino;

import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.Collections;

public class TreinoValidator extends Validator<Treino> {

    @Override
    public Notification validar(Treino treino) {

        Notification notification = new Notification();

        if (treino == null) {
            notification.addErro("Treino é nulo.");
            return notification;
        }
        if (nuloOuVazio(treino.getNome())) {
            notification.addErro("Nome é nulo ou vazio");
            return notification;
        }
        if (nuloOuVazio(treino.getObservacao())) {
            notification.addErro("Observacao é nula ou vazia");
            return notification;
        }

        return notification;
    }
}
