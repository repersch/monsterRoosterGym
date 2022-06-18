package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.SqliteFichaTreinoDAO;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class TabelaFichaTreinoUIController {
    @FXML
    public Button btnLogOut;
    @FXML
    public Button btnCriarFichaTreino;
    @FXML
    public Button btnEditarFichaTreino;
    @FXML
    public Button btnCancelar;
    @FXML
    private Label txtAlunoLogado;
    @FXML
    private TableView<FichaTreino> tabelaFichaTreino;
    @FXML
    private TableColumn<FichaTreino, String> cIdFichaTreino;
    @FXML
    private TableColumn<FichaTreino, String> cDataInicio;
    @FXML
    private TableColumn<FichaTreino, String> cValidadeFichaTreino;
    @FXML
    private TableColumn<Usuario, String> cInstrutorFichaTreino;
    @FXML
    private Button btnDetalhesFichaTreino;

    private ObservableList<FichaTreino> fichasTreino;
    private Usuario usuarioAutenticado;
    private Usuario usuarioSelecionado;
    private FichaTreino fichaTreinoSelecionada;

    private BuscarFichaTreinoUC buscarFichaTreinoUC;
    private BuscarUsuarioUC buscarUsuarioUC;

    @FXML
    public void initialize() {

        buscarFichaTreinoUC = new BuscarFichaTreinoUC(new SqliteFichaTreinoDAO());
        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());

        WindowLoader.addOnChangeScreenListener(new WindowLoader.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Dados dados) {
                fichasTreino = FXCollections.observableArrayList();
                tabelaFichaTreino.setItems(fichasTreino);

                if (dados.getIdUsuarioAutenticado() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();

                    if (!usuarioAutenticado.getInstrutor()) {
                        btnCriarFichaTreino.setVisible(false);
                        btnEditarFichaTreino.setVisible(false);
                        btnCancelar.setLayoutX(btnCriarFichaTreino.getLayoutX());
                    }
                }
                if (dados.getIdAuxiliar() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).isPresent()) {
                    usuarioSelecionado = buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).get();
                    carregarDadosDaEntidadeNaView();
                }

                cIdFichaTreino.setCellValueFactory(new PropertyValueFactory<>("id"));
                cDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
                cValidadeFichaTreino.setCellValueFactory(new PropertyValueFactory<>("validade"));
                // descobrir como faz pra aparecer só o nome do instrutor
                cInstrutorFichaTreino.setCellValueFactory(new PropertyValueFactory<>("instrutor"));

                txtAlunoLogado.setText(usuarioAutenticado.getNome());

                carregarTabela();
            }
        });
    }

    private void carregarDadosDaEntidadeNaView() {

    }

    private void carregarTabela() {
        List<FichaTreino> todasFichaTreinos = buscarFichaTreinoUC.buscarPorAluno(usuarioSelecionado);
        fichasTreino.clear();
        fichasTreino.addAll(todasFichaTreinos);
    }

    public void telaDetalhesFichaTreino(ActionEvent actionEvent) throws IOException {
        fichaTreinoSelecionada = tabelaFichaTreino.getSelectionModel().getSelectedItem();
        if (fichaTreinoSelecionada == null) {
            showAlert("Erro!", "Selecione um exercício.", Alert.AlertType.ERROR);
            return;
        }
        WindowLoader.setRoot("aluno/DetalhesFichaTreinoUI", new Dados(usuarioAutenticado.getId(), usuarioSelecionado.getId(), fichaTreinoSelecionada.getId()));
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AutenticacaoUI", new Dados(0, 0, 0));
    }

    public void editarFichaTreino(ActionEvent actionEvent) throws IOException {
        fichaTreinoSelecionada = tabelaFichaTreino.getSelectionModel().getSelectedItem();
        if (fichaTreinoSelecionada == null) {
            showAlert("Erro!", "Selecione um exercício.", Alert.AlertType.ERROR);
            return;
        }
//        aqui eu envio a fichaTreino como idAuxiliar2, através dele é possível encontrar o usuário
        WindowLoader.setRoot("aluno/GerenciarFichaTreinoUI", new Dados(usuarioAutenticado.getId(), usuarioSelecionado.getId(), fichaTreinoSelecionada.getId()));
    }

    public void criarFichaTreino(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/GerenciarFichaTreinoUI", new Dados(usuarioAutenticado.getId(), usuarioSelecionado.getId(), 0));
    }

    public void voltarParaTelaAnterior(ActionEvent actionEvent) throws IOException {
        if (usuarioAutenticado.getInstrutor()) {
            WindowLoader.setRoot("instrutor/TabelaAlunoUI", new Dados(usuarioAutenticado.getId(), 0, 0));
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }


}
