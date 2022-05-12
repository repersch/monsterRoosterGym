package br.edu.ifsp.domain.usecases.aluno;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class BuscarUsuarioUC {

    private UsuarioDAO usuarioDAO;

    public BuscarUsuarioUC(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

        public Optional<Usuario> buscarPorId(Integer id) {
            if (id == null) {
                throw new IllegalArgumentException("Id não pode ser nulo.");
            }

            return usuarioDAO.findById(id);
        }

        public Optional<Usuario> buscarPorCpf (String cpf) {
            if (Validator.nuloOuVazio(cpf)) {
                throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
            }

            return usuarioDAO.findByAttribute("cpf", cpf);
        }

        public Optional<Usuario> buscarPorNome (String nome) {
            if (Validator.nuloOuVazio(nome)) {
                throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
            }

            return usuarioDAO.findByAttribute("nome", nome);
        }

        public List<Usuario> buscarTodosInstrutores () {
            return usuarioDAO.findAllInstrutores();
        }

        public List<Aluno> buscarTodosAlunos () {
            return usuarioDAO.findAllAlunos();
        }

        public List<Usuario> buscarTodos() {
            return usuarioDAO.findAll();
        }

}
