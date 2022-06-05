package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.CriarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.EditarUsuarioUC;
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

    private Usuario instrutorParaSalvar = new Usuario();
    private Usuario instrutorSelecionado;
    private Usuario usuarioAutenticado;
    private BuscarUsuarioUC buscarUsuarioUC;

    @FXML
    protected void initialize() {
        WindowLoader.addOnChangeScreenListener(new WindowLoader.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Dados dados) {
                buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());

                if (dados.getIdUsuarioAutenticado() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();
                }
                if (dados.getIdAuxiliar() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).isPresent()) {
                    instrutorSelecionado = buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).get();
                    instrutorParaSalvar.setId(instrutorSelecionado.getId());
                    carregarDadosDaEntidadeParaView();
                }

            }
        });
    }

    public void voltarParaCenaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaInstrutorUI", new Dados(usuarioAutenticado.getId(), 0, 0));
    }

    private void carregarDadosDaViewNaEntidade() {
        instrutorParaSalvar.setNome(txtNomeInstrutor.getText());
        instrutorParaSalvar.setEmail(txtEmailnstrutor.getText());
        instrutorParaSalvar.setSenha(txtSenha.getText());
        instrutorParaSalvar.setInstrutor(true);
        instrutorParaSalvar.setAluno(null);
    }

    public void criarOuEditarInstrutor(ActionEvent actionEvent) throws IOException {
        carregarDadosDaViewNaEntidade();

        if (instrutorSelecionado == null) {
            CriarUsuarioUC criarUsuarioUC = new CriarUsuarioUC(new SqliteUsuarioDAO());
            criarUsuarioUC.salvar(instrutorParaSalvar);
        } else {
            EditarUsuarioUC editarUsuarioUC = new EditarUsuarioUC(new SqliteUsuarioDAO());
            editarUsuarioUC.atualizar(instrutorParaSalvar);
        }
        voltarParaCenaAnterior(actionEvent);
    }

    public void carregarDadosDaEntidadeParaView() {
        if (instrutorSelecionado == null) {
            throw new IllegalArgumentException("Instrutor não pode ser nulo.");
        }
        txtNomeInstrutor.setText(instrutorSelecionado.getNome());
        txtEmailnstrutor.setText(instrutorSelecionado.getEmail());
        txtSenha.setText(instrutorSelecionado.getSenha());
    }
}
