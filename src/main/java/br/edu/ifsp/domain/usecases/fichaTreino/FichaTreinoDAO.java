package br.edu.ifsp.domain.usecases.fichaTreino;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.usecases.utils.DAO;
import java.util.List;

public interface FichaTreinoDAO  extends DAO<FichaTreino, Integer> {

    List<FichaTreino> findByAluno (Aluno aluno);
}
