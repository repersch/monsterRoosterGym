package br.edu.ifsp.domain.usecases.utils;

import java.util.Collection;

public abstract class Validator<T> {

    public abstract Notification validar(T type);

    public static boolean nuloOuVazio(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean nuloOuVazio(Collection collection) {
        return collection == null || collection.isEmpty();
    }

}
