package br.edu.ifsp.domain.usecases.utils;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String mensagem) {
        super((mensagem));
    }

}

