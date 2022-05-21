package br.edu.ifsp.domain.usecases.exercicioTreino;

import br.edu.ifsp.domain.entities.ExercicioTreino;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.Collection;
import java.util.Collections;

public class ExercicioTreinoValidator extends Validator<ExercicioTreino> {

    @Override
    public Notification validar(ExercicioTreino exercicioTreino) {
        Notification notification = new Notification();

        if (exercicioTreino == null) {
            notification.addErro("Exercício é nulo.");
            return notification;
        }
        if (nuloOuVazio(exercicioTreino.getSerie().toString())) {
            notification.addErro("Serie é nula ou vazia.");
            return notification;
        }
        if (nuloOuVazio(exercicioTreino.getCarga().toString())) {
            notification.addErro("Carga é nula ou vazia.");
            return notification;
        }
        if (nuloOuVazio(exercicioTreino.getRepeticao().toString())) {
            notification.addErro("Repeticao é nula ou vazia.");
            return notification;
        }
        if (nuloOuVazio(exercicioTreino.getTreino().toString())) {
            notification.addErro("Treino é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(exercicioTreino.getExercicio().toString())) {
            notification.addErro("Exercicio é nulo ou vazio.");
            return notification;
        }
        return notification;
    }
}
