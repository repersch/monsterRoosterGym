package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.application.controller.instrutor.GerenciarExercicioUIController;
import br.edu.ifsp.application.repository.dao.SqliteExercicioDAO;
import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.usecases.exercicio.BuscarExercicioUC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class TabelaExercicioUIController {
    @FXML
    public Button btnEditarExercicio;
    @FXML
    private TableColumn cNomeExercicio;
    @FXML
    private TableColumn cGrupoMuscular;
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
    private TableView tabelaExercicio;


    private ObservableList<Exercicio> exercicios;
    private Exercicio exercicioSelecionado;
    BuscarExercicioUC buscarExercicioUC = new BuscarExercicioUC(new SqliteExercicioDAO());
    private Object usuarioLogado;

    @FXML
    public void initialize() {
        cNomeExercicio.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cGrupoMuscular.setCellValueFactory(new PropertyValueFactory<>("grupoMuscular"));
        exercicios = FXCollections.observableArrayList();
        tabelaExercicio.setItems(exercicios);
        carregarTableView();
        filtrarDadosDaTabela();
    }

    private void carregarTableView() {
        List<Exercicio> todosExercicios = buscarExercicioUC.buscarTodos();
        exercicios.clear();
        exercicios.addAll(todosExercicios);
    }

    public void selecionar(MouseEvent mouseEvent) {
        this.exercicioSelecionado = (Exercicio) tabelaExercicio.getSelectionModel().getSelectedItem();
    }

    public void cadastrarNovoExercicio(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/GerenciarExercicioUI");
    }

    public void editarExercicio(ActionEvent actionEvent) throws IOException {
        if (exercicioSelecionado != null) {
            WindowLoader.setRoot("instrutor/GerenciarExercicioUI");
            GerenciarExercicioUIController controller = (GerenciarExercicioUIController) WindowLoader.getController();
            controller.carregarDadosDaEntidadeNaView(exercicioSelecionado);
        }
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
        this.usuarioLogado = null;
        WindowLoader.setRoot("AutenticacaoUI");
    }


    public void telaAluno(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaAlunoUI");
    }

    public void telaInstrutor(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaInstrutorUI");
    }

    public void telaExercicio(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("TabelaExercicioUI");
    }

    public void telaRelatorio(ActionEvent actionEvent) {
//        WindowLoader.setRoot("instrutor/TabelaRelatorioUI");
    }

}
