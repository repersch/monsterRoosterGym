package br.edu.ifsp.application.repository.dao;

import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.usecases.treino.TreinoDAO;

import java.util.List;
import java.util.Optional;

public class SqliteTreinoDAO implements TreinoDAO {
    @Override
    public Integer create(Treino type) {
        return null;
    }

    @Override
    public Optional<Treino> findByAttribute(String attribute, String key) {
        return Optional.empty();
    }

    @Override
    public Optional<Treino> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Treino> findAll() {
        return null;
    }

    @Override
    public boolean update(Treino type) {
        return false;
    }
}
