package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.SqliteFichaTreinoDAO;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
    public Label lbNome;
    @FXML
    public Label lbTelefone;
    @FXML
    public Label lbPeso;
    @FXML
    public Label lbAltura;
    @FXML
    public Label lbObservacao;
    @FXML
    private Label txtAlunoLogado;
    @FXML
    private TableView<FichaTreino> tabelaFichaTreino;
    @FXML
    private TableColumn<FichaTreino, String> cIdFichaTreino;
    @FXML
    private TableColumn<FichaTreino, LocalDate> cDataInicio;
    @FXML
    private TableColumn<FichaTreino, LocalDate> cValidadeFichaTreino;
    @FXML
    private TableColumn<FichaTreino, String> cInstrutorFichaTreino;
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
                        btnCancelar.setVisible(false);
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
                cInstrutorFichaTreino.setCellValueFactory(info -> new SimpleStringProperty(info.getValue().getInstrutor().getNome()));

                txtAlunoLogado.setText(usuarioAutenticado.getNome());

                carregarTabela();
            }
        });
    }

    private void carregarDadosDaEntidadeNaView() {
        lbNome.setText(usuarioSelecionado.getNome());
        lbTelefone.setText(usuarioSelecionado.getAluno().getTelefone());
        lbAltura.setText(String.valueOf(usuarioSelecionado.getAluno().getAltura()));
        lbPeso.setText(String.valueOf(usuarioSelecionado.getAluno().getPeso()));
        lbObservacao.setText(usuarioSelecionado.getAluno().getObservacoes());
    }

    private void carregarTabela() {
        List<FichaTreino> todasFichaTreinos = buscarFichaTreinoUC.buscarPorAluno(usuarioSelecionado);
        fichasTreino.clear();
        fichasTreino.addAll(todasFichaTreinos);
    }

    public void telaDetalhesFichaTreino(ActionEvent actionEvent) throws IOException {
        fichaTreinoSelecionada = tabelaFichaTreino.getSelectionModel().getSelectedItem();
        if (fichaTreinoSelecionada == null) {
            showAlert("Erro!", "Selecione uma ficha.", Alert.AlertType.ERROR);
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
            showAlert("Erro!", "Selecione uma ficha.", Alert.AlertType.ERROR);
            return;
        }
//       fichaTreino = idAuxiliar2, atrav??s dele ?? poss??vel encontrar o usu??rio
        WindowLoader.setRoot("aluno/GerenciarFichaTreinoUI", new Dados(usuarioAutenticado.getId(),
                                    usuarioSelecionado.getId(), fichaTreinoSelecionada.getId()));
    }

    public void criarFichaTreino(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/GerenciarFichaTreinoUI", new Dados(usuarioAutenticado.getId(),
                                                    usuarioSelecionado.getId(), 0));
    }

    public void voltarParaTelaAnterior(ActionEvent actionEvent) throws IOException {
        if (usuarioAutenticado.getInstrutor()) {
            WindowLoader.setRoot("instrutor/TabelaAlunoUI", new Dados(usuarioAutenticado.getId(),
                                                                0, 0));
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
