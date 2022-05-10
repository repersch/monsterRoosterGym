package br.edu.ifsp.domain.usecases.exercicio;

import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class ExercicioValidator extends Validator<Exercicio> {

    @Override
    public Notification validar(Exercicio exercicio) {
        Notification notification = new Notification();

        if (exercicio == null) {
            notification.addErro("Exercício é nulo.");
            return notification;
        }
        if (nuloOuVazio(exercicio.getNome())) {
            notification.addErro("Nome é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(exercicio.getGrupoMuscular().toString())) {
            notification.addErro("Grupo muscular é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(exercicio.getDescricao())) {
            notification.addErro("Descrição é nula ou vazia.");
            return notification;
        }
        if (nuloOuVazio(exercicio.getEmUso().toString())) {
            notification.addErro("Em uso é nulo ou vazio.");
            return notification;
        }
        return notification;
    }
}
