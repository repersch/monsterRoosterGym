package br.edu.ifsp.domain.usecases.exercicio;

import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class BuscarExercicioUC {
    private ExercicioDAO exercicioDAO;

    public BuscarExercicioUC(ExercicioDAO exercicioDAO) {
        this.exercicioDAO = exercicioDAO;
    }

    public Optional<Exercicio> buscarPorId(Integer id) {
        if (id == null) {
            throw  new IllegalArgumentException("Id não pode ser nulo");
        }
        return exercicioDAO.findById(id);
    }

    public Optional<Exercicio> buscarPorNome (String nome) {
        if (Validator.nuloOuVazio(nome)) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        return exercicioDAO.findByAttribute("nome", nome);
    }

    public List<Exercicio> buscarTodos() {
        return exercicioDAO.findAll();
    }

}
