package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class EditarUsuarioUC {

    private UsuarioDAO usuarioDAO;

    public EditarUsuarioUC(UsuarioDAO alunoDAO) {
        this.usuarioDAO = alunoDAO;
    }

    public boolean atualizar(Usuario usuario) {

        Validator<Usuario> validator = new UsuarioValidator();
        Notification notificacao = validator.validar(usuario);

        if (notificacao.possuiErros()) {
           throw  new IllegalArgumentException(notificacao.mensagemDeErro());
        }

        Integer id = usuario.getId();

        if (usuarioDAO.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado.");
        }

        return usuarioDAO.update(usuario);
    }
}
