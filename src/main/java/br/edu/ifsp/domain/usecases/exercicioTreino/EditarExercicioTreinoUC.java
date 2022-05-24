package br.edu.ifsp.domain.usecases.exercicioTreino;

import br.edu.ifsp.domain.entities.ExercicioTreino;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class EditarExercicioTreinoUC {
    private ExercicioTreinoDAO exercicioTreinoDAO;

    public EditarExercicioTreinoUC(ExercicioTreinoDAO exercicioTreinoDAO) {
        this.exercicioTreinoDAO = exercicioTreinoDAO;
    }

    public boolean atualizar(ExercicioTreino exercicioTreino) {
        Validator<ExercicioTreino> validator = new ExercicioTreinoValidator();
        Notification notificacao = validator.validar(exercicioTreino);

        if (notificacao.possuiErros()) {
            throw  new IllegalArgumentException(notificacao.mensagemDeErro());
        }

        Integer id = exercicioTreino.getId();

        if (exercicioTreinoDAO.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Exercício Treino não encontrado.");
        }
        return exercicioTreinoDAO.update(exercicioTreino);
    }
}
