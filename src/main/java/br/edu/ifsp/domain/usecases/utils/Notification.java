package br.edu.ifsp.domain.usecases.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {

    private List<Error> erros = new ArrayList<>();

    public void addErro(String mensagem) {
        addErro(mensagem, null);
    }

    private void addErro(String mensagem, Exception e) {
        addErro(mensagem, e);
    }

    public boolean semErros() {
        return erros.isEmpty();
    }

    public boolean possuiErros() {
        return !semErros();
    }

    private static class Erro {
        String mensagem;
        Exception causa;

        public Erro(String mensagem, Exception causa) {
            this.mensagem = mensagem;
            this.causa = causa;
        }
    }

    public  String mensagemDeErro() {
        return erros.stream()
                .map(e -> e.getMessage())
                .collect(Collectors.joining(", "));
    }

}
