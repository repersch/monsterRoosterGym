package br.edu.ifsp.domain.usecases.treino;

import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class BuscarTreinoUC {
    private TreinoDAO treinoDAO;

    public BuscarTreinoUC(TreinoDAO treinoDAO) {
        this.treinoDAO = treinoDAO;
    }

    public Optional<Treino> buscarPorId(Integer id) {
        if (id == null) {
            throw  new IllegalArgumentException("Id não pode ser nulo");
        }
        return treinoDAO.findById(id);
    }

    public Optional<Treino> buscarPorNome (String nome) {
        if (Validator.nuloOuVazio(nome)) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        return treinoDAO.findByAttribute("nome", nome);
    }

    public List<Treino> buscarTodos() {
        return treinoDAO.findAll();
    }
}
