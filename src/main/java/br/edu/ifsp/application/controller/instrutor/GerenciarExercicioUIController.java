package br.edu.ifsp.application.controller.instrutor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GerenciarExercicioUIController {
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvarExercicio;
    @FXML
    private TextField txtNomeExercicio;
    @FXML
    private TextField txtGrupoMuscular;
    @FXML
    private TextArea txtDescricaoExercicio;

    public void voltarParaCenaAnterior(ActionEvent actionEvent) {
    }

    public void salvarExercicio(ActionEvent actionEvent) {
    }
}
