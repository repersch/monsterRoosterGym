package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
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
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class TabelaInstrutorUIController {
    @FXML
    private TableView tabelaInstrutor;
    @FXML
    private TableColumn cNomeInstrutor;
    @FXML
    private Label txtAlunoLogado;
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
    public Usuario instrutorSelecionado;
    BuscarUsuarioUC buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
    public Usuario usuarioLogado;

    ResourceBundle rb = new ResourceBundle() {
        @Override
        protected Object handleGetObject(String key) {
            return null;
        }

        @Override
        public Enumeration<String> getKeys() {
            return null;
        }
    };

    @FXML
    public void initialize() {
        cNomeInstrutor.setCellValueFactory(new PropertyValueFactory<>("nome"));
        instrutores = FXCollections.observableArrayList();
        tabelaInstrutor.setItems(instrutores);
        carregarTableView();
        filtrarDadosDaTabela();
    }

    private void carregarTableView() {
        List<Usuario> todosInstrutores = buscarUsuarioUC.buscarTodosInstrutores();
        instrutores.clear();
        instrutores.addAll(todosInstrutores);
    }


    public void selecionar(MouseEvent mouseEvent) {
        this.instrutorSelecionado = (Usuario) tabelaInstrutor.getSelectionModel().getSelectedItem();
    }


    public void cadastrarNovoInstrutor(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/GerenciarInstrutorUI", rb);
    }

    public void editarInstrutor(ActionEvent actionEvent) throws IOException {
        if (this.instrutorSelecionado != null) {
            WindowLoader.setRoot("instrutor/GerenciarInstrutorUI", rb);
            GerenciarInstrutorUIController controller = new GerenciarInstrutorUIController();
            controller.carregarDadosDaEntidadeParaView(this.instrutorSelecionado);
        }

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
        this.usuarioLogado = null;
        WindowLoader.setRoot("AutenticacaoUI", rb);
    }


    public void telaAluno(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaAlunoUI", rb);
    }

    public void telaInstrutor(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaInstrutorUI", rb);
    }

    public void telaExercicio(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("TabelaExercicioUI", rb);
    }

    public void telaRelatorio(ActionEvent actionEvent) {
//        WindowLoader.setRoot("instrutor/TabelaRelatorioUI", rb);
    }

}
