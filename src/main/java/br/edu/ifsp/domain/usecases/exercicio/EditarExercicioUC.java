package br.edu.ifsp.domain.usecases.exercicio;

import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class EditarExercicioUC {
    private ExercicioDAO exercicioDAO;

    public EditarExercicioUC(ExercicioDAO exercicioDAO) {
        this.exercicioDAO = exercicioDAO;
    }

    public boolean atualizar(Exercicio exercicio) {
        Validator<Exercicio> validator = new ExercicioValidator();
        Notification notificacao = validator.validar(exercicio);

        if (notificacao.possuiErros()) {
            throw  new IllegalArgumentException(notificacao.mensagemDeErro());
        }

        Integer id = exercicio.getId();

        if (exercicioDAO.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Exercício não encontrado.");
        }
        return exercicioDAO.update(exercicio);
    }
}
