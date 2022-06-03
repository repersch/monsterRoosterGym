package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class DetalhesFichaTreinoUIController {

    @FXML
    public Label txtAlunoLogado;

    private Usuario usuarioLogado;

    public void telaDetalhesFichaTreino(ActionEvent actionEvent) {
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        this.usuarioLogado = null;
        WindowLoader.setRoot("AutenticacaoUI");
    }
}
