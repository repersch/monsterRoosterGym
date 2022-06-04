package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.autenticar.AutenticarUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class AutenticacaoUIController {
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnEntrar;

    private Usuario usuarioAutenticado;
    private Dados dados;
    AutenticarUC autenticarUC = new AutenticarUC(new BuscarUsuarioUC(new SqliteUsuarioDAO()));

    public void autenticar(ActionEvent actionEvent) throws IOException {
       usuarioAutenticado =  autenticarUC.autenticar(txtEmail.getText(), txtSenha.getText());
       dados = new Dados(usuarioAutenticado.getId(), 0, 0);

       if (usuarioAutenticado.getInstrutor()) {
           WindowLoader.setRoot("instrutor/TabelaAlunoUI", dados);
       } else {
           dados.setIdAuxiliar(usuarioAutenticado.getId());
           WindowLoader.setRoot("aluno/TabelaFichaTreinoUI", dados);
       }
    }


}
