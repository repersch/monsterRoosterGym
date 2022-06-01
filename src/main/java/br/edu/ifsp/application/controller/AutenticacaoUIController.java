package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.autenticar.AutenticarUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class AutenticacaoUIController {
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnEntrar;

    private static Usuario usuarioAutenticado;
    AutenticarUC autenticarUC = new AutenticarUC(new BuscarUsuarioUC(new SqliteUsuarioDAO()));

    public void autenticar(ActionEvent actionEvent) throws IOException {
       usuarioAutenticado =  autenticarUC.autenticar(txtEmail.getText(), txtSenha.getText());

        ResourceBundle recursos = new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                if (key.contains("id")) {
                    return usuarioAutenticado;
                }
                return null;
            }

            @Override
            public Enumeration<String> getKeys() {
                return null;
            }
        };

       if (usuarioAutenticado.getInstrutor()) {
           WindowLoader.setRoot("instrutor/TabelaAlunoUI", recursos);
       } else {
           WindowLoader.setRoot("aluno/FichaTreinoUI", recursos);
       }
    }


}
