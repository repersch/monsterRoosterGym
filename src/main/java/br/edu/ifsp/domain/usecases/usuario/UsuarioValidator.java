package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.Collections;

public class UsuarioValidator extends Validator<Usuario> {

    @Override
    public Notification validar(Usuario usuario) {

        Notification notification = new Notification();

        if (usuario == null) {
            notification.addErro("Usuário é nulo.");
            return notification;
        }
        if (nuloOuVazio(usuario.getNome())) {
            notification.addErro("Nome é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(usuario.getEmail())) {
            notification.addErro("E-mail é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(usuario.getSenha())) {
            notification.addErro("Senha é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(Collections.singleton(usuario.getInstrutor()))) {
            notification.addErro("É Instrutor é nulo ou vazio.");
            return notification;
        }


        return notification;
    }
}
