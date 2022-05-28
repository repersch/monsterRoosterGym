package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.WindowLoader;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.CriarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GerenciarInstrutorUIController {
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField txtNomeInstrutor;
    @FXML
    private TextField txtEmailnstrutor;
    @FXML
    private PasswordField txtSenha;

    private Usuario instrutor;

    CriarUsuarioUC criarUsuarioUC = new CriarUsuarioUC(new SqliteUsuarioDAO());

    public void voltarParaCenaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("application/view/instrutor/TabelaInstrutorUI");
    }

    private void carregarDadosDaViewNaEntidade() {
        if (instrutor == null) {
            instrutor = new Usuario();
        }
        instrutor.setNome(txtNomeInstrutor.getText());
        instrutor.setEmail(txtEmailnstrutor.getText());
        instrutor.setSenha(txtSenha.getText());
        instrutor.setInstrutor(true);
        instrutor.setAluno(null);
    }

    public void salvarInstrutor(ActionEvent actionEvent) throws IOException {
        carregarDadosDaViewNaEntidade();
        criarUsuarioUC.salvar(instrutor);
        voltarParaCenaAnterior(actionEvent);
    }


    public void carregarDadosDaEntidadeParaView(Usuario instrutorSelecionado) {
        if (instrutorSelecionado == null) {
            throw new IllegalArgumentException("Instrutor não pode ser nulo.");
        }
        this.instrutor = instrutorSelecionado;
        txtNomeInstrutor.setText(instrutor.getNome());
        txtEmailnstrutor.setText(instrutor.getEmail());

    }
}
