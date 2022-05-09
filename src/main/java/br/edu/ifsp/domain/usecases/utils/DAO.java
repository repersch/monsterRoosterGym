package br.edu.ifsp.domain.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO<T, K> {

    K create(T type);

    Optional<T> findByAttribute(String attribute, String key);

    Optional<T> findById(K id);

    List<T> findAll();

    boolean update(T type);

}
