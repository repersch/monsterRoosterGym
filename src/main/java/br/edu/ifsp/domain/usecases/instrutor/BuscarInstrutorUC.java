package br.edu.ifsp.domain.usecases.instrutor;

import br.edu.ifsp.domain.entities.Instrutor;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class BuscarInstrutorUC {

    private InstrutorDAO instrutorDAO;

    public BuscarInstrutorUC(InstrutorDAO instrutorDAO) {
        this.instrutorDAO = instrutorDAO;
    }

    public Optional<Instrutor> buscarPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo.");
        }

        return instrutorDAO.findById(id);
    }

    public Optional<Instrutor> buscarPorNome(String nome) {
        if (Validator.nuloOuVazio(nome)) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }

        return instrutorDAO.findByAttribute("nome", nome);
    }

    public Optional<Instrutor> buscarPorEmail(String email) {
        if (Validator.nuloOuVazio(email)) {
            throw new IllegalArgumentException("E-mail não pode ser nulo ou vazio.");
        }

        return instrutorDAO.findByAttribute("email", email);
    }

    public List<Instrutor> buscarTodos() {
        return instrutorDAO.findAll();
    }

}
