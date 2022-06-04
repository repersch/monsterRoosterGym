package br.edu.ifsp.domain.usecases.treino;

import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface TreinoDAO extends DAO<Treino,Integer> {

    Optional<Treino> findByAttribute(String attribute, String key);

    List<Treino> findByIdFichaTreino(Integer idFichaTreino);
}
