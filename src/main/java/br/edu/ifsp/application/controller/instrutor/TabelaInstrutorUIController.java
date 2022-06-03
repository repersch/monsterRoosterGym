package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class TabelaInstrutorUIController {
    @FXML
    private TableView<Usuario> tabelaInstrutor;
    @FXML
    private TableColumn<Usuario, String> cNomeInstrutor;
    @FXML
    private Label txtUsuarioLogado;
    @FXML
    private TextField txtBuscarInstrutor;
    @FXML
    private Button btnMenuAluno;
    @FXML
    private Button btnMenuInstrutor;
    @FXML
    private Button btnMenuExercicio;
    @FXML
    private Button btnMenuRelatorio;
    @FXML
    private Button btnNovoInstrutor;

    private ObservableList<Usuario> instrutores;
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
                txtUsuarioLogado.setText(usuarioAutenticado.getNome());

                cNomeInstrutor.setCellValueFactory(new PropertyValueFactory<>("nome"));
                instrutores = FXCollections.observableArrayList();

                tabelaInstrutor.setItems(instrutores);
                carregarTableView();
                filtrarDadosDaTabela();
            }
        });
    }

    private void carregarTableView() {
        List<Usuario> todosInstrutores = buscarUsuarioUC.buscarTodosInstrutores();
        instrutores.clear();
        instrutores.addAll(todosInstrutores);
    }

    public void cadastrarNovoInstrutor(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/GerenciarInstrutorUI", new Dados(usuarioAutenticado.getId(), 0));
    }

    public void editarInstrutor(ActionEvent actionEvent) throws IOException {
        Usuario instrutorSelecionado = tabelaInstrutor.getSelectionModel().getSelectedItem();
        if (instrutorSelecionado == null) {
            showAlert("Erro!", "Selecione um instrutor.", Alert.AlertType.ERROR);
            return;
        }
        WindowLoader.setRoot("instrutor/GerenciarInstrutorUI", new Dados(usuarioAutenticado.getId(), instrutorSelecionado.getId()));
    }

    private void filtrarDadosDaTabela() {
        FilteredList<Usuario> dadosFiltrados = new FilteredList<>(instrutores, a -> true);
        txtBuscarInstrutor.textProperty().addListener((observado, valorAntigo, valorNovo) -> {
            dadosFiltrados.setPredicate(instrutor -> {
                if (valorNovo == null || valorNovo.isEmpty()) {
                    return true;
                }

                String filtroEmLowerCase = valorNovo.toLowerCase();

                if (instrutor.getNome().toLowerCase().contains(filtroEmLowerCase)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Usuario> dadosBuscados = new SortedList<>(dadosFiltrados);
        dadosBuscados.comparatorProperty().bind(tabelaInstrutor.comparatorProperty());
        tabelaInstrutor.setItems(dadosBuscados);
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AutenticacaoUI", new Dados(usuarioAutenticado.getId(), 0));
    }


    public void telaAluno(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaAlunoUI", new Dados(usuarioAutenticado.getId(), 0));
    }

    public void telaInstrutor(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaInstrutorUI", new Dados(usuarioAutenticado.getId(), 0));
    }

    public void telaExercicio(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("TabelaExercicioUI", new Dados(usuarioAutenticado.getId(), 0));
    }

    public void telaRelatorio(ActionEvent actionEvent) {
//        WindowLoader.setRoot("instrutor/TabelaRelatorioUI", new Dados(usuarioAutenticado.getId(), 0));
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

}
