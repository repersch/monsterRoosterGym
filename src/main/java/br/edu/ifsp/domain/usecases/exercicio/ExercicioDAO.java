package br.edu.ifsp.domain.usecases.exercicio;

import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface ExercicioDAO extends DAO<Exercicio, Integer> {
    boolean deleteById(Integer key);
    boolean delete(Exercicio exercicio);

    Optional<Exercicio> findByAttribute(String atributo, String chave);
}
