package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.application.controller.instrutor.GerenciarExercicioUIController;
import br.edu.ifsp.application.repository.dao.SqliteExercicioDAO;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.entities.GrupoMuscular;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.exercicio.BuscarExercicioUC;
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

public class TabelaExercicioUIController {
    @FXML
    public Button btnEditarExercicio;
    @FXML
    public Label txtUsuarioLogado;
    @FXML
    private TableColumn<Exercicio, String> cNomeExercicio;
    @FXML
    private TableColumn<GrupoMuscular, String> cGrupoMuscular;
    @FXML
    private TextField txtBuscarExercicio;
    @FXML
    private Button btnMenuAluno;
    @FXML
    private Button btnMenuInstrutor;
    @FXML
    private Button btnMenuExercicio;
    @FXML
    private Button btnMenuRelatorio;
    @FXML
    private Button btnNovoExercicio;
    @FXML
    private TableView<Exercicio> tabelaExercicio;


    private ObservableList<Exercicio> exercicios;
    private Exercicio exercicioSelecionado;
    private Usuario usuarioAutenticado;
    private BuscarExercicioUC buscarExercicioUC;
    private BuscarUsuarioUC buscarUsuarioUC;

    @FXML
    protected void initialize() {
        WindowLoader.addOnChangeScreenListener(new WindowLoader.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Dados dados) {
                buscarExercicioUC = new BuscarExercicioUC(new SqliteExercicioDAO());
                buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());

                if (dados.getIdUsuarioAutenticado() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();
                }

                txtUsuarioLogado.setText(usuarioAutenticado.getNome());

                exercicios = FXCollections.observableArrayList();
                tabelaExercicio.setItems(exercicios);

                cNomeExercicio.setCellValueFactory(new PropertyValueFactory<>("nome"));
                cGrupoMuscular.setCellValueFactory(new PropertyValueFactory<>("grupoMuscular"));

                carregarTableView();
                filtrarDadosDaTabela();
            }
        });
    }

    private void carregarTableView() {
        List<Exercicio> todosExercicios = buscarExercicioUC.buscarTodos();
        exercicios.clear();
        exercicios.addAll(todosExercicios);
    }

    public void cadastrarNovoExercicio(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/GerenciarExercicioUI", new Dados(usuarioAutenticado.getId(), 0, 0));
    }

    public void editarExercicio(ActionEvent actionEvent) throws IOException {
        exercicioSelecionado = tabelaExercicio.getSelectionModel().getSelectedItem();
        if (exercicioSelecionado == null) {
            showAlert("Erro!", "Selecione um exercício.", Alert.AlertType.ERROR);
            return;
        }
        WindowLoader.setRoot("instrutor/GerenciarExercicioUI", new Dados(usuarioAutenticado.getId(), exercicioSelecionado.getId()));
    }

    private void filtrarDadosDaTabela() {
        FilteredList<Exercicio> dadosFiltrados = new FilteredList<>(exercicios, a -> true);
        txtBuscarExercicio.textProperty().addListener((observado, valorAntigo, valorNovo) -> {
            dadosFiltrados.setPredicate(exercicio -> {
                if (valorNovo == null || valorNovo.isEmpty()) {
                    return true;
                }

                String filtroEmLowerCase = valorNovo.toLowerCase();

                if (exercicio.getNome().toLowerCase().contains(filtroEmLowerCase) ||
                    exercicio.getGrupoMuscular().toString().toLowerCase().contains(filtroEmLowerCase)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Exercicio> dadosBuscados = new SortedList<>(dadosFiltrados);
        dadosBuscados.comparatorProperty().bind(tabelaExercicio.comparatorProperty());
        tabelaExercicio.setItems(dadosBuscados);
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AutenticacaoUI", new Dados(0,0));
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
