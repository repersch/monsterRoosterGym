package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarUsuarioUC {

    private UsuarioDAO usuarioDAO;

    public CriarUsuarioUC(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Integer salvar(Usuario usuario) {

        Validator<Usuario> validator = new UsuarioValidator();
        Notification notification = validator.validar(usuario);

        if (notification.possuiErros()) {
            throw new IllegalArgumentException(notification.mensagemDeErro());
        }

        String emailUsuario = usuario.getEmail();

        if (usuarioDAO.findByAttribute("email", emailUsuario).isPresent()) {
            throw new EntityAlreadyExistsException("E-mail já cadastrado.");
        }

        return usuarioDAO.create(usuario);

    }
}
