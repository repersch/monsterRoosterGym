package br.edu.ifsp.domain.usecases.instrutor;

import br.edu.ifsp.domain.usecases.aluno.UsuarioDAO;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CriarInstrutorUC {

    private UsuarioDAO instrutorDAO;

    public CriarInstrutorUC(UsuarioDAO instrutorDAO) {
        this.instrutorDAO = instrutorDAO;
    }

//    public Integer salvar(br.edu.ifsp.domain.entities.Usuario instrutor) {
//
//        Validator<br.edu.ifsp.domain.entities.Usuario> validator = new UsuarioValidator();
//        Notification notification = validator.validar(instrutor);
//
//        if (notification.possuiErros()) {
//            throw new IllegalArgumentException(notification.mensagemDeErro());
//        }
//
//        if (notification.possuiErros()) {
//            throw new IllegalArgumentException(notification.mensagemDeErro());
//        }
//
//        String nome = instrutor.getNome();
//
//        if (instrutorDAO.findByAttribute("nome", nome).isPresent()) {
//            throw new EntityAlreadyExistsException("Nome já cadastrado.");
//        }
//
//        return instrutorDAO.create(instrutor);
//    }
}
