package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.application.controller.instrutor.TabelaAlunoUIController;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.autenticar.AutenticarUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AutenticacaoUIController {
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnEntrar;

    private static Usuario usuarioAutenticado;
    AutenticarUC autenticarUC = new AutenticarUC(new SqliteUsuarioDAO());

    // pensar numa forma de levar o usuario que esta autenticado junto
    public void autenticar(ActionEvent actionEvent) throws IOException {
       usuarioAutenticado =  autenticarUC.autenticar(txtEmail.getText(), txtSenha.getText());
       if (usuarioAutenticado.getInstrutor()) {
           WindowLoader.setRoot("instrutor/TabelaAlunoUI");
//           TabelaAlunoUIController tabelaAlunoUIController = (TabelaAlunoUIController) WindowLoader.getController();
//           tabelaAlunoUIController.setUsuarioLogado(usuarioAutenticado.getNome());
       } else {
           WindowLoader.setRoot("aluno/FichaTreinoUI");
       }
    }


}
