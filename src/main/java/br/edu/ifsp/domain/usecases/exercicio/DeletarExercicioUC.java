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
        return exercicioDAO.deleteById(id);
    }

    public boolean remover(Exercicio exercicio) {
        if (exercicio == null || exercicioDAO.findById(exercicio.getId()).isEmpty()) {
            throw new EntityNotFoundException("Exercício não encontrado.");
        }
        return exercicioDAO.delete(exercicio);
    }
}
