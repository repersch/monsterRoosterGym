package br.edu.ifsp.domain.usecases.exercicio;

import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class DeletarExercicioUC {
    private ExercicioDAO exercicioDAO;

    public DeletarExercicioUC(ExercicioDAO exercicioDAO) {
        this.exercicioDAO = exercicioDAO;
    }

    public boolean remover(Integer id) {
        if (id == null || exercicioDAO.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Exercício não encontrado.");
        }

        if (exercicioDAO.findById(id).get().getEmUso()) {
            throw new IllegalArgumentException("Exercício está em uso e não pode ser excluído.");
        }

        return exercicioDAO.deleteById(id);
    }

    public boolean remover(Exercicio exercicio) {
        return remover(exercicio.getId());
    }
}
