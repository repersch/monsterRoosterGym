package br.edu.ifsp.application.controller.instrutor;

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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class RelatorioFichaTreinoUIController {
    @FXML
    public Button btnLogOut;
    @FXML
    public Button btnCancelar;
    @FXML
    public DatePicker dpDataInicio;
    @FXML
    public DatePicker dpDataValidade;
    @FXML
    public Button btnBuscar;
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

    private ObservableList<FichaTreino> fichasTreino;
    private Usuario usuarioAutenticado;
    private Usuario usuarioSelecionado;
    private FichaTreino fichaTreinoSelecionada;
    private FichaTreino fichaTreinoParaBuscar;

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
        fichaTreinoParaBuscar = new FichaTreino();
        fichaTreinoParaBuscar.setDataInicio(dpDataInicio.getValue());
        fichaTreinoParaBuscar.setValidade(dpDataValidade.getValue());
    }

    private void carregarTabela() {
        List<FichaTreino> todasFichaTreinos = buscarFichaTreinoUC.buscarPorAluno(usuarioSelecionado);
        fichasTreino.clear();
        fichasTreino.addAll(todasFichaTreinos);
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AutenticacaoUI", new Dados(0, 0, 0));
    }

    public void voltarParaTelaAnterior(ActionEvent actionEvent) throws IOException {
        if (usuarioAutenticado.getInstrutor()) {
            WindowLoader.setRoot("instrutor/RelatorioUI", new Dados(usuarioAutenticado.getId(), 0, 0));
        }
    }

    public void buscarFichasData(ActionEvent actionEvent) {
        if (dpDataInicio.getValue() == null || dpDataValidade.getValue() == null){
            showAlert("Erro!", "Escolha um valor nos campos de Data Início e Data Validade.", Alert.AlertType.ERROR);
            return;
        }
        FilteredList<FichaTreino> dadosFiltrados = new FilteredList<>(fichasTreino, a -> true);
        dadosFiltrados.setPredicate(fichaTreino ->
                fichaTreino.getDataInicio().isAfter(dpDataInicio.getValue()) && fichaTreino.getValidade().isBefore(dpDataValidade.getValue()));

        tabelaFichaTreino.setItems(dadosFiltrados);
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

}
