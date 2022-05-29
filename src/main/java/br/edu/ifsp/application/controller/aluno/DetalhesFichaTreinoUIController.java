package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.controller.AutenticacaoUIController;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Usuario;
import javafx.event.ActionEvent;

import java.io.IOException;
import static br.edu.ifsp.application.controller.AutenticacaoUIController.*;

public class DetalhesFichaTreinoUIController {

    private Usuario usuarioLogado;

    public void telaDetalhesFichaTreino(ActionEvent actionEvent) {
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        this.usuarioLogado = null;
        WindowLoader.setRoot("AutenticacaoUI");
    }
}
