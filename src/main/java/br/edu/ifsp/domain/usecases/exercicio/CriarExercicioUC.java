package br.edu.ifsp.domain.usecases.exercicio;

import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarExercicioUC {
    private ExercicioDAO exercicioDAO;

    public CriarExercicioUC(ExercicioDAO exercicioDAO) {
        this.exercicioDAO = exercicioDAO;
    }

    public Integer salvar(Exercicio exercicio) {
        Validator<Exercicio> validator = new ExercicioValidator();
        Notification notification = validator.validar(exercicio);

        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

        String nomeExercicio = exercicio.getNome();

        if (exercicioDAO.findByAttribute("nome", nomeExercicio).isPresent()) {
            throw new EntityAlreadyExistsException("Exercício já cadastrado.");
        }
        return exercicioDAO.create(exercicio);
    }
}
