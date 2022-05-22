package br.edu.ifsp.domain.usecases.exercicioTreino;

import br.edu.ifsp.domain.entities.ExercicioTreino;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface ExercicioTreinoDAO extends DAO<ExercicioTreino, Integer>  {

    Optional<ExercicioTreino> findByAttribute(String attribute, String key);

    boolean updateExerciseLoad(Integer exercicioTreinoId, Double load);
}
