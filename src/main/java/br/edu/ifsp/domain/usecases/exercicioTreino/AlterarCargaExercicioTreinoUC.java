package br.edu.ifsp.domain.usecases.exercicioTreino;

import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class AlterarCargaExercicioTreinoUC {
    private ExercicioTreinoDAO exercicioTreinoDAO;

    public AlterarCargaExercicioTreinoUC(ExercicioTreinoDAO exercicioTreinoDAO) {
        this.exercicioTreinoDAO = exercicioTreinoDAO;
    }

    public boolean atualizarCargaExercicio(Integer exercicioTreinoId, Double load) {
        if (exercicioTreinoDAO.findById(exercicioTreinoId).isEmpty()) {
            throw new EntityNotFoundException("Exercício Treino não encontrado.");
        }
        return exercicioTreinoDAO.updateExerciseLoad(exercicioTreinoId, load);
    }
}
