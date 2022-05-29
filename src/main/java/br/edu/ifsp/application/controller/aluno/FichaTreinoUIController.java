package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class FichaTreinoUIController {
    @FXML
    private Label txtAlunoLogado;
    @FXML
    private TableView tabelaFichaTreino;
    @FXML
    private TableColumn cIdFichaTreino;
    @FXML
    private TableColumn cDataInicio;
    @FXML
    private TableColumn cValidadeFichaTreino;
    @FXML
    private TableColumn cInstrutorFichaTreino;
    @FXML
    private Button btnDetalhesFichaTreino;
    @FXML
    private Button btnIniciarTreino;
    @FXML
    private Button btnFinalizarTreino;
    private Usuario usuarioLogado;

    public void selecionar(MouseEvent mouseEvent) {
    }

    public void telaDetalhesFichaTreino(ActionEvent actionEvent) {
    }

    public void registrarInicioTreino(ActionEvent actionEvent) {
    }

    public void registrarFinalTreino(ActionEvent actionEvent) {
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        this.usuarioLogado = null;
        WindowLoader.setRoot("AutenticacaoUI");
    }
}
