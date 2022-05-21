package br.edu.ifsp.domain.usecases.fichaTreino;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.DAO;
import java.util.List;
import java.util.Optional;

public interface FichaTreinoDAO  extends DAO<FichaTreino, Integer> {

    List<FichaTreino> findByAluno (Usuario aluno);

    Optional<FichaTreino> findByAttribute(String attribute, String key);
}
