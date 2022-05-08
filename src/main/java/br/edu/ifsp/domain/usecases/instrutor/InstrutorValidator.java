package br.edu.ifsp.domain.usecases.instrutor;


import br.edu.ifsp.domain.entities.Instrutor;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class InstrutorValidator extends Validator<Instrutor> {

    @Override
    public Notification validar(Instrutor instrutor) {

        Notification notification = new Notification();

        if (instrutor == null) {
            notification.addErro("Instrutor é nulo.");
            return notification;
        }
        if (nuloOuVazio(instrutor.getNome())) {
            notification.addErro("Nome é nulo ou vazio.");
            return notification;
        }
        return notification;
    }
}
