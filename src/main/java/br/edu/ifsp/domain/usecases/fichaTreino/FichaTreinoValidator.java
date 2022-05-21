package br.edu.ifsp.domain.usecases.fichaTreino;

import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class FichaTreinoValidator extends Validator<FichaTreino> {

    public Notification validar(FichaTreino fichaTreino) {
        Notification notification = new Notification();

        if (fichaTreino == null) {
            notification.addErro("Ficha treino é nula.");
            return notification;
        }
        if (nuloOuVazio(fichaTreino.getDataInicio().toString())) {
            notification.addErro("Data inicio é nula ou vazia.");
            return notification;
        }
        if (nuloOuVazio(fichaTreino.getValidade().toString())) {
            notification.addErro("Validade é nula ou vazia.");
            return notification;
        }
        if (nuloOuVazio(fichaTreino.getAluno().toString())) {
            notification.addErro("Aluno é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(fichaTreino.getInstrutor().toString())) {
            notification.addErro("Em uso é nulo ou vazio.");
            return notification;
        }
        return notification;
    }
}
