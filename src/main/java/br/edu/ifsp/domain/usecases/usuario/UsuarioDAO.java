package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO extends DAO<Usuario, Integer> {

    List<Usuario> findAll(String tipoUsuario);

    Optional<Usuario> findByAttribute(String attribute, String key);
}
