package br.edu.ifsp.domain.usecases.aluno;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.List;

public interface UsuarioDAO extends DAO<Usuario, Integer> {


    List<Usuario> findAllInstrutores();

    List<Aluno> findAllAlunos();

}
