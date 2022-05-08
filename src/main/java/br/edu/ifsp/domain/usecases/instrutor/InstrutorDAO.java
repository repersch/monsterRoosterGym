package br.edu.ifsp.domain.usecases.instrutor;

import br.edu.ifsp.domain.entities.Instrutor;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface InstrutorDAO extends DAO<Instrutor, Integer> {


    Optional<Instrutor> findByNome(String nome);
}
