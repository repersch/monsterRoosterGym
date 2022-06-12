package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.CriarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.EditarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

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

    private Usuario usuarioAutenticado;
    private Usuario alunoSelecionado;
    private Usuario alunoCriadoOuModificado;

    private BuscarUsuarioUC buscarUsuarioUC;


    @FXML
    protected void initialize() {
        WindowLoader.addOnChangeScreenListener(new WindowLoader.OnChangeScreen() {

            @Override
            public void onScreenChanged(String newScreen, Dados dados) {
                buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
                alunoCriadoOuModificado = new Usuario();

                if (dados.getIdAuxiliar() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).isPresent()) {
                    alunoSelecionado = buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).get();
                    carregarDadosDaEntidadeNaView();
                    alunoCriadoOuModificado.setId(alunoSelecionado.getId());
                }

                if (dados.getIdUsuarioAutenticado() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();
                }

            }
        });
    }

    private void carregarDadosDaEntidadeNaView() {
        txtNomeAluno.setText(alunoSelecionado.getNome());
        txtEmailAluno.setText(alunoSelecionado.getEmail());
        txtTelefone.setText(alunoSelecionado.getAluno().getTelefone());
        txtCpfAluno.setText(alunoSelecionado.getAluno().getCpf());
        txtSenha.setText(alunoSelecionado.getSenha());
        dpDataNascimento.setValue(alunoSelecionado.getAluno().getDataNascimento());
        txtPeso.setText(String.valueOf(alunoSelecionado.getAluno().getPeso()));
        txtAltura.setText(String.valueOf(alunoSelecionado.getAluno().getAltura()));
        txtObservacoes.setText(alunoSelecionado.getAluno().getObservacoes());
    }


    public void carregarDadosDaViewNaEntidade() {
        alunoCriadoOuModificado.setNome(txtNomeAluno.getText());
        alunoCriadoOuModificado.setEmail(txtEmailAluno.getText());
        alunoCriadoOuModificado.setSenha(txtSenha.getText());
        alunoCriadoOuModificado.setInstrutor(false);
        Aluno dadosAluno = new Aluno();
        dadosAluno.setCpf(txtCpfAluno.getText());
        dadosAluno.setTelefone(txtTelefone.getText());
        dadosAluno.setGenero("Feminino");
        dadosAluno.setAltura(Double.valueOf(txtAltura.getText()));
        dadosAluno.setPeso(Double.valueOf(txtPeso.getText()));
        dadosAluno.setObservacoes(txtObservacoes.getText());
        dadosAluno.setDataNascimento(dpDataNascimento.getValue());
        alunoCriadoOuModificado.setAluno(dadosAluno);
    }

    public void salvarAluno(ActionEvent actionEvent) throws IOException {
        carregarDadosDaViewNaEntidade();

        if (alunoSelecionado == null) {
            CriarUsuarioUC criarUsuarioUC = new CriarUsuarioUC(new SqliteUsuarioDAO());
            criarUsuarioUC.salvar(alunoCriadoOuModificado);
        } else {
            EditarUsuarioUC editarUsuarioUC = new EditarUsuarioUC(new SqliteUsuarioDAO());
            editarUsuarioUC.atualizar(alunoCriadoOuModificado);
        }
        voltarParaCenaAnterior(actionEvent);
    }

    public void voltarParaCenaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaAlunoUI", new Dados(usuarioAutenticado.getId(), 0, 0));
    }

}
