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
import java.util.List;

public class TabelaAlunoUIController {
    @FXML
    public Button btnLogOut;
    @FXML
    private Button btnEditarAluno;
    @FXML
    private TableView<Usuario> tabelaAluno;
    @FXML
    private TableColumn<Object, Object> cNomeAluno;
    @FXML
    private TableColumn<Object, Object> cCpfAluno;
    @FXML
    private TextField txtBuscarAluno;
    @FXML
    private Button btnMenuAluno;
    @FXML
    private Button btnMenuInstrutor;
    @FXML
    private Button btnMenuExercicio;
    @FXML
    private Button btnMenuRelatorio;
    @FXML
    private Button btnNovoAluno;
    @FXML
    private Label txtAlunoLogado;

    private ObservableList<Usuario> alunos;
    public Usuario alunoSelecionado;
    public Usuario usuarioLogado;
    BuscarUsuarioUC buscarUsuarioUC;

    @FXML
    private void initialize () {
        alunos = FXCollections.observableArrayList();
        tabelaAluno.setItems(alunos);

        cNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
        // precisa mudar para cpf
        cCpfAluno.setCellValueFactory(new PropertyValueFactory<>("email"));

//        setUsuarioLogado(usuarioLogado.getNome());
//        txtAlunoLogado.setText(usuarioLogado.getNome());
        carregarTabela();
        filtrarDadosDaTabela();
    }

    private void carregarTabela() {
        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
        List<Usuario> todosAlunos = buscarUsuarioUC.buscarTodosAlunos();
        alunos.clear();
        alunos.addAll(todosAlunos);
    }


    private void filtrarDadosDaTabela() {
        FilteredList<Usuario> dadosFiltrados = new FilteredList<>(alunos, a -> true);
        txtBuscarAluno.textProperty().addListener((observado, valorAntigo, valorNovo) -> {
            dadosFiltrados.setPredicate(aluno -> {
                if (valorNovo == null || valorNovo.isEmpty()) {
                    return true;
                }

                String filtroEmLowerCase = valorNovo.toLowerCase();

                if (aluno.getNome().toLowerCase().contains(filtroEmLowerCase) ||
                        aluno.getEmail().toLowerCase().contains(filtroEmLowerCase)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Usuario> dadosBuscados = new SortedList<>(dadosFiltrados);
        dadosBuscados.comparatorProperty().bind(tabelaAluno.comparatorProperty());
        tabelaAluno.setItems(dadosBuscados);
    }

    public void setUsuarioLogado(String nome) {
        this.usuarioLogado = buscarUsuarioUC.buscarPorNome(nome).isPresent() ? buscarUsuarioUC.buscarPorNome(nome).get() : null;
    }

    public void selecionar(MouseEvent mouseEvent) {
        this.alunoSelecionado = (Usuario) tabelaAluno.getSelectionModel().getSelectedItem();
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

    public void cadastrarNovoAluno(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/GerenciarAlunoUI");
    }

    public void editarAluno(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/GerenciarAlunoUI");
    }


    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        this.usuarioLogado = null;
        WindowLoader.setRoot("AutenticacaoUI");
    }
}
