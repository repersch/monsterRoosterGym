package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.controller.aluno.FichaTreinoUIController;
import br.edu.ifsp.application.controller.instrutor.TabelaAlunoUIController;
import br.edu.ifsp.application.main.Main;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.autenticar.AutenticarUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
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

    private Usuario usuarioAutenticado;
    private Dados dados;
    AutenticarUC autenticarUC = new AutenticarUC(new BuscarUsuarioUC(new SqliteUsuarioDAO()));

//    @FXML
//    protected void initialize() {
//        WindowLoader.addOnChngeScreenListener(new WindowLoader.OnChangeScreen() {
//
//            @Override
//            public void onScreenChanged(String newScreen, Object userData) {
//
//            }
//        });
//    }



    public void autenticar(ActionEvent actionEvent) throws IOException {
       usuarioAutenticado =  autenticarUC.autenticar(txtEmail.getText(), txtSenha.getText());
        dados = new Dados(usuarioAutenticado.getId(), null);

       if (usuarioAutenticado.getInstrutor()) {
           WindowLoader.setRoot("instrutor/TabelaAlunoUI", dados);
//           TabelaAlunoUIController tabelaAlunoUIController = new TabelaAlunoUIController();
//           tabelaAlunoUIController.setUsuarioAutenticado(usuarioAutenticado);
       } else {
           WindowLoader.setRoot("aluno/FichaTreinoUI", dados);
//           FichaTreinoUIController fichaTreinoUIController = new FichaTreinoUIController();
//           fichaTreinoUIController.setUsuarioLogado(usuarioAutenticado);
       }
    }


}
