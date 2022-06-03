package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.entities.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class DetalhesFichaTreinoUIController {

    @FXML
    public Label txtAlunoLogado;
    @FXML
    public Button btnDetalhesFichaTreino;
    @FXML
    public Button btnLogOut;
    @FXML
    public Button btnCancelar;
    @FXML
    public ListView<Treino> listaTreinos;

    private Usuario usuarioAutenticado;
    private Usuario usuarioSelecionado;
    private FichaTreino fichaTreinoSelecionada;

    public void telaDetalhesFichaTreino(ActionEvent actionEvent) {
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AutenticacaoUI", new Dados(0, 0));
    }

    public void voltarParaTelaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/TabelaFichaTreinoUI", new Dados(usuarioAutenticado.getId(), 0));
    }
}
