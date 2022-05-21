package br.edu.ifsp.domain.usecases.fichaTreino;

import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class EditarFichaTreinoUC {
    private FichaTreinoDAO fichaTreinoDAO;

    public EditarFichaTreinoUC(FichaTreinoDAO fichaTreinoDAO) {
        this.fichaTreinoDAO = fichaTreinoDAO;
    }

    public boolean atualizar(FichaTreino fichaTreino) {
        Validator<FichaTreino> validator = new FichaTreinoValidator();
        Notification notificacao = validator.validar(fichaTreino);

        if (notificacao.possuiErros()) {
            throw  new IllegalArgumentException(notificacao.mensagemDeErro());
        }
//        Integer id = fichaTreino.getId();
//
//        if (exercicioDAO.findById(id).isEmpty()) {
//            throw new EntityNotFoundException("Exercício não encontrado.");
//        }
        return fichaTreinoDAO.update(fichaTreino);
    }
}
