package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.application.controller.AutenticacaoUIController;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
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

    private Dados dados;

    public Usuario usuarioAutenticado;
    BuscarUsuarioUC buscarUsuarioUC;

    public TabelaAlunoUIController() {
        this.buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
    }


    @FXML
    protected void initialize() {
        WindowLoader.addOnChngeScreenListener(new WindowLoader.OnChangeScreen() {

            @Override
            public void onScreenChanged(String newScreen, Dados dados) {
                alunos = FXCollections.observableArrayList();
                tabelaAluno.setItems(alunos);
                if (buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();
                }

                cNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
                // precisa mudar para cpf
                cCpfAluno.setCellValueFactory(new PropertyValueFactory<>("email"));

                txtAlunoLogado.setText(usuarioAutenticado.getNome());

                carregarTabela();
                filtrarDadosDaTabela();
            }
        });
    }



    private void carregarTabela() {
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

    public void telaAluno(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaAlunoUI", new Dados(usuarioAutenticado.getId(), 0));
    }

    public void telaInstrutor(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/TabelaInstrutorUI",  new Dados(usuarioAutenticado.getId(), 0));
    }

    public void telaExercicio(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("TabelaExercicioUI", new Dados(usuarioAutenticado.getId(), 0));
    }

    public void telaRelatorio(ActionEvent actionEvent) {
//        WindowLoader.setRoot("instrutor/TabelaRelatorioUI");
    }

    public void cadastrarNovoAluno(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("instrutor/GerenciarAlunoUI",  new Dados(usuarioAutenticado.getId(), 0));
    }

    public void editarAluno(ActionEvent actionEvent) throws IOException {
        Usuario alunoSelecionado = tabelaAluno.getSelectionModel().getSelectedItem();
        dados = new Dados(usuarioAutenticado.getId(), alunoSelecionado.getId());
        WindowLoader.setRoot("instrutor/GerenciarAlunoUI",  dados);
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
//        dados.setIdUsuarioAutenticado(null);
//        dados.setIdAuxiliar(null);
        WindowLoader.setRoot("AutenticacaoUI");
    }

}
