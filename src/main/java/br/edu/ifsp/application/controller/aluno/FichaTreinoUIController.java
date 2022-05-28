package br.edu.ifsp.application.controller.aluno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

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

    public void selecionar(MouseEvent mouseEvent) {
    }

    public void telaDetalhesFichaTreino(ActionEvent actionEvent) {
    }

    public void registrarInicioTreino(ActionEvent actionEvent) {
    }

    public void registrarFinalTreino(ActionEvent actionEvent) {
    }
}
