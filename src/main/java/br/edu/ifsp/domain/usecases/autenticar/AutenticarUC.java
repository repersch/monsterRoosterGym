package br.edu.ifsp.domain.usecases.autenticar;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AutenticarUC {

//    private UsuarioDAO usuarioDAO;

    private BuscarUsuarioUC buscarUsuarioUC;

//    public AutenticarUC(UsuarioDAO usuarioDAO) {
//        this.usuarioDAO = usuarioDAO;
//    }


    public AutenticarUC(BuscarUsuarioUC buscarUsuarioUC) {
        this.buscarUsuarioUC = buscarUsuarioUC;
    }

    public Usuario autenticar (String email, String senha) {

        Usuario usuarioLogado = null;

        if (Validator.nuloOuVazio(email)) {
            throw new IllegalArgumentException("E-mail não pode ser nulo ou vazio.");
        }
        if (Validator.nuloOuVazio(senha)) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia.");
        }
        if (buscarUsuarioUC.buscarPorEmail(email).isEmpty()) {
            throw new EntityNotFoundException("E-mail não cadastrado.");

        }

//        if (usuarioDAO.findByAttribute("email", email).isEmpty()) {
//           throw new EntityNotFoundException("E-mail não cadastrado.");
//        }

//        usuarioLogado = usuarioDAO.findByAttribute("email", email).get();

        usuarioLogado = buscarUsuarioUC.buscarPorEmail(email).get();

        if (!usuarioLogado.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

      return usuarioLogado;
    }

}
