package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class EditarUsuarioUC {

    private UsuarioDAO alunoDAO;

    public EditarUsuarioUC(UsuarioDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public boolean atualizar(Usuario usuario) {

        Validator<Usuario> validator = new UsuarioValidator();
        Notification notificacao = validator.validar(usuario);

        if (notificacao.possuiErros()) {
           throw  new IllegalArgumentException(notificacao.mensagemDeErro());
        }

        Integer id = usuario.getId();

        if (alunoDAO.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Aluno não encontrado.");
        }

        return alunoDAO.update(usuario);
    }
}
