package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.SqliteFichaTreinoDAO;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.CriarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.EditarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

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

    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarFichaTreinoUC buscarFichaTreinoUC;
    private Usuario usuarioAutenticado;
    private FichaTreino fichaTreinoSelecionada;
    private FichaTreino fichaTreinoParaSalvar;
    private Usuario alunoSelecionado;


    @FXML
    public void initialize() {

        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
        buscarFichaTreinoUC = new BuscarFichaTreinoUC(new SqliteFichaTreinoDAO());
        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());

        WindowLoader.addOnChangeScreenListener(new WindowLoader.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Dados dados) {

                if (dados.getIdUsuarioAutenticado() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();
                }
                if (dados.getIdAuxiliar() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).isPresent()) {
                    alunoSelecionado = buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).get();
                }
                if (dados.getIdAuxiliar2() > 0
                        && buscarFichaTreinoUC.buscarPorId(dados.getIdAuxiliar2()).isPresent()) {
                    fichaTreinoSelecionada = buscarFichaTreinoUC.buscarPorId(dados.getIdAuxiliar2()).get();
                    carregarDadosDaEntidadeNaView();
                } else {
                    txtBuscarAluno.setText(alunoSelecionado.getNome());
                    lbNomeAluno.setText(alunoSelecionado.getNome());
                    txtBuscarInstrutor.setText(usuarioAutenticado.getNome());
                    lbNomeInstrutor.setText(usuarioAutenticado.getNome());
                }
            }
        });
    }

    private void carregarDadosDaEntidadeNaView() {
        dpDataInicio.setValue(fichaTreinoSelecionada.getDataInicio());
        dpDataValidade.setValue(fichaTreinoSelecionada.getValidade());
        txtBuscarAluno.setText(fichaTreinoSelecionada.getAluno().getNome());
        lbNomeAluno.setText(fichaTreinoSelecionada.getAluno().getNome());
        txtBuscarInstrutor.setText(fichaTreinoSelecionada.getInstrutor().getNome());
        lbNomeInstrutor.setText(fichaTreinoSelecionada.getInstrutor().getNome());
    }

    public void buscarAluno(ActionEvent actionEvent) {
        Optional<Usuario> resultado = buscarUsuarioUC.buscarPorNome(txtBuscarAluno.getText());
        Usuario alunoBuscado;
        if (resultado.isPresent()) {
            alunoBuscado = resultado.get();
            lbNomeAluno.setText(alunoBuscado.getNome());
        } else {
            alunoBuscado = null;
            lbNomeAluno.setText("");
            showAlert("Erro!", "Aluno não encontrado.", Alert.AlertType.ERROR);
        }
    }

    public void buscarInstrutor(ActionEvent actionEvent) {
        Optional<Usuario> resultado = buscarUsuarioUC.buscarPorNome(txtBuscarInstrutor.getText());
        Usuario instrutorBuscado;
        if (resultado.isPresent()) {
            instrutorBuscado = resultado.get();
            lbNomeInstrutor.setText(instrutorBuscado.getNome());
        } else {
            instrutorBuscado = null;
            lbNomeInstrutor.setText("");
            showAlert("Erro!", "Instrutor não encontrado.", Alert.AlertType.ERROR);
        }
    }

    public void salvarFichaTreino(ActionEvent actionEvent) throws IOException {
        carregarDadosDaViewNaEntidade();

        if (fichaTreinoSelecionada == null) {
            CriarFichaTreinoUC criarFichaTreinoUC = new CriarFichaTreinoUC(new SqliteFichaTreinoDAO());
            criarFichaTreinoUC.salvar(fichaTreinoParaSalvar);
        } else {
            EditarFichaTreinoUC editarFichaTreinoUC = new EditarFichaTreinoUC(new SqliteFichaTreinoDAO());
            fichaTreinoParaSalvar.setId(fichaTreinoSelecionada.getId());
            editarFichaTreinoUC.atualizar(fichaTreinoParaSalvar);
        }
        voltarParaTelaAnterior(actionEvent);
    }

    private void carregarDadosDaViewNaEntidade() {
        fichaTreinoParaSalvar = new FichaTreino();
        fichaTreinoParaSalvar.setDataInicio(dpDataInicio.getValue());
        fichaTreinoParaSalvar.setValidade(dpDataValidade.getValue());
        fichaTreinoParaSalvar.setAluno(buscarUsuarioUC.buscarPorNome(txtBuscarAluno.getText()).get());
        fichaTreinoParaSalvar.setInstrutor(buscarUsuarioUC.buscarPorNome(txtBuscarInstrutor.getText()).get());
        if (dpDataValidade.getValue().isAfter(LocalDate.now())) {
            fichaTreinoParaSalvar.setValido(true);
        } else {
            fichaTreinoParaSalvar.setValido(false);
        }
    }

    public void voltarParaTelaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/TabelaFichaTreinoUI",
                new Dados(usuarioAutenticado.getId(), alunoSelecionado.getId(), fichaTreinoSelecionada != null ? fichaTreinoSelecionada.getId() : 0));
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

}