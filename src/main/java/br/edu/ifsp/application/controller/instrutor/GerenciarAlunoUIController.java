package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.WindowLoader;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.CriarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

public class GerenciarAlunoUIController {
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField txtNomeAluno;
    @FXML
    private TextField txtEmailAluno;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtCpfAluno;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtPeso;
    @FXML
    private DatePicker dpDataNascimento;
    @FXML
    private TextField txtAltura;
    @FXML
    private TextArea txtObservacoes;

    private Usuario aluno;
    CriarUsuarioUC criarUsuarioUC = new CriarUsuarioUC(new SqliteUsuarioDAO());

    public void voltarParaCenaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("application/view/instrutor/TabelaAlunoUI");
    }

    public void carregarDadosDaViewNaEntidade() {
        if (aluno == null) {
            aluno = new Usuario();
        }
        aluno.setNome(txtNomeAluno.getText());
        aluno.setEmail(txtEmailAluno.getText());
        aluno.setSenha(txtSenha.getText());
        aluno.setInstrutor(false);
        Aluno dadosAluno = new Aluno();
        dadosAluno.setCpf(txtCpfAluno.getText());
        dadosAluno.setTelefone(txtTelefone.getText());
        dadosAluno.setGenero("Feminino");
        dadosAluno.setAltura(Double.valueOf(txtAltura.getText()));
        dadosAluno.setPeso(Double.valueOf(txtPeso.getText()));
        dadosAluno.setObservacoes(txtObservacoes.getText());
        dadosAluno.setDataNascimento((LocalDate) dpDataNascimento.getValue());
        aluno.setAluno(dadosAluno);
    }

    public void salvarAluno(ActionEvent actionEvent) throws IOException {
        carregarDadosDaViewNaEntidade();
        criarUsuarioUC.salvar(aluno);
        voltarParaCenaAnterior(actionEvent);
    }
}
