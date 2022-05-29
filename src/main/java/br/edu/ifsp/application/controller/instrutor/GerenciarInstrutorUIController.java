package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.CriarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import static br.edu.ifsp.domain.usecases.usuario.CriarUsuarioUC.*;

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

    public Usuario instrutor;

    CriarUsuarioUC criarUsuarioUC = new CriarUsuarioUC(new SqliteUsuarioDAO());

    public void voltarParaCenaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaInstrutorUI");
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
        System.out.printf("Cheguei aqui: " + instrutorSelecionado);
        if (instrutorSelecionado == null) {
            throw new IllegalArgumentException("Instrutor não pode ser nulo.");
        }
        this.instrutor = instrutorSelecionado;
        txtNomeInstrutor.autosize();
        txtNomeInstrutor.setText(instrutorSelecionado.getNome());
        txtEmailnstrutor.setText(instrutorSelecionado.getEmail());

    }
}
