package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.List;

public interface UsuarioDAO extends DAO<Usuario, Integer> {

    List<Usuario> findAll(String tipoUsuario);

}
