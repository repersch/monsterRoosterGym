package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Enumeration;
import java.util.Optional;
import java.util.ResourceBundle;

public class GerenciarFichaTreinoUIController {
    @FXML
    public TextField txtBuscarAluno;
    @FXML
    public DatePicker dpDataInicio;
    @FXML
    public DatePicker dpDataValidade;
    @FXML
    public TextField txtBuscarInstrutor;
    @FXML
    public Button btnBuscarAluno;
    @FXML
    public Button btnBuscarInstrutor;
    @FXML
    public Label lbNomeAluno;
    @FXML
    public Label lbNomeInstrutor;

    BuscarUsuarioUC buscarUsuarioUC;
    Usuario aluno;
    Usuario instrutor;

    public GerenciarFichaTreinoUIController() {
        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
    }

    public void buscarAluno(ActionEvent actionEvent) {
        Optional<Usuario> resultado = buscarUsuarioUC.buscarPorNome(txtBuscarAluno.getText());
        if (resultado.isPresent()) {
            aluno = resultado.get();
            lbNomeAluno.setText(aluno.getNome());
        } else {
            aluno = null;
            lbNomeAluno.setText("");
            showAlert("Erro!", "Aluno não encontrado.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void buscarInstrutor(ActionEvent actionEvent) {
        Optional<Usuario> resultado = buscarUsuarioUC.buscarPorNome(txtBuscarInstrutor.getText());
        if (resultado.isPresent()) {
            instrutor = resultado.get();
            lbNomeInstrutor.setText(instrutor.getNome());
        } else {
            instrutor = null;
            lbNomeInstrutor.setText("");
            showAlert("Erro!", "Instrutor não encontrado.", Alert.AlertType.ERROR);
        }
    }
}
