package br.edu.ifsp.domain.usecases.exercicioTreino;

import br.edu.ifsp.domain.entities.ExercicioTreino;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarExercicioTreinoUC {
    private ExercicioTreinoDAO exercicioTreinoDAO;

    public CriarExercicioTreinoUC(ExercicioTreinoDAO exercicioTreinoDAO) {
        this.exercicioTreinoDAO = exercicioTreinoDAO;
    }

    public Integer salvar(ExercicioTreino exercicioTreino) {
        Validator<ExercicioTreino> validator = new ExercicioTreinoValidator();
        Notification notification = validator.validar(exercicioTreino);
        System.out.println(exercicioTreino.getExercicio());
        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

//       Integer idExercicioTreino = exercicioTreino.getId();
//
//        if (exercicioTreinoDAO.findById(idExercicioTreino).isPresent()) {
//            throw new EntityAlreadyExistsException("Exercicio Treino já cadastrada.");
//        }
        return exercicioTreinoDAO.create(exercicioTreino);
    }
}
