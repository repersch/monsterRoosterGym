package br.edu.ifsp.domain.usecases.exercicioTreino;

import br.edu.ifsp.domain.entities.ExercicioTreino;

import java.util.List;
import java.util.Optional;

public class BuscarExercicioTreinoUC {
    private ExercicioTreinoDAO exercicioTreinoDAO;

    public BuscarExercicioTreinoUC(ExercicioTreinoDAO exercicioTreinoDAO) {
        this.exercicioTreinoDAO = exercicioTreinoDAO;
    }

    public Optional<ExercicioTreino> buscarPorId(Integer id) {
        if (id == null) {
            throw  new IllegalArgumentException("Id não pode ser nulo");
        }
        return exercicioTreinoDAO.findById(id);
    }

    public List<ExercicioTreino> buscarTodos() {
        return exercicioTreinoDAO.findAll();
    }
}
