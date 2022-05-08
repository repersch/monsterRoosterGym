package br.edu.ifsp.domain.usecases.aluno;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.Collections;

public class AlunoValidator extends Validator<Aluno> {

    @Override
    public Notification validar(Aluno aluno) {

        Notification notification = new Notification();

        if (aluno == null) {
            notification.addErro("Aluno é nulo.");
            return notification;
        }
        if (nuloOuVazio(aluno.getCpf())) {
            notification.addErro("CPF é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(aluno.getNome())) {
            notification.addErro("Nome é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(aluno.getEmail())) {
            notification.addErro("E-mail é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(aluno.getTelefone())) {
            notification.addErro("Telefone é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(aluno.getGenero())) {
            notification.addErro("Gênero é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(Collections.singleton(aluno.getDataNascimento()))) {
            notification.addErro("Data de nascimento é nula ou vazia.");
            return notification;
        }
        if (nuloOuVazio(Collections.singleton(aluno.getPeso()))) {
            notification.addErro("Peso é nulo ou vazio.");
            return notification;
        }
        if (nuloOuVazio(Collections.singleton(aluno.getAltura()))) {
            notification.addErro("Altura é nula ou vazia.");
            return notification;
        }

        return notification;
    }
}
