package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.SqliteFichaTreinoDAO;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FichaTreinoUIController {
    @FXML
    public Button btnLogOut;
    @FXML
    public Button btnCriarFichaTreino;
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
    private TableColumn<FichaTreino, String> cInstrutorFichaTreino;
    @FXML
    private Button btnDetalhesFichaTreino;
    @FXML
    private Button btnIniciarTreino;
    @FXML
    private Button btnFinalizarTreino;

    private ObservableList<FichaTreino> fichasTreino;
    private Usuario usuarioLogado;
    private FichaTreino fichaTreinoSelecionada;

    BuscarFichaTreinoUC buscarFichaTreinoUC;
    BuscarUsuarioUC buscarUsuarioUC;


    public FichaTreinoUIController() {
        buscarFichaTreinoUC = new BuscarFichaTreinoUC(new SqliteFichaTreinoDAO());
        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
        // aluno está mockado aqui, não sei como fazer para passar o usuário de uma tela para outra
//        usuarioLogado = buscarUsuarioUC.buscarPorId(2).get();
    }

    @FXML
    public void initialize() {
        fichasTreino = FXCollections.observableArrayList();
        tabelaFichaTreino.setItems(fichasTreino);

        cIdFichaTreino.setCellValueFactory(new PropertyValueFactory<>("id"));
        cDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        cValidadeFichaTreino.setCellValueFactory(new PropertyValueFactory<>("validade"));
        // descobrir como faz pra aparecer só o nome do instrutor
        cInstrutorFichaTreino.setCellValueFactory(new PropertyValueFactory<>("instrutor"));

        this.txtAlunoLogado.setText(usuarioLogado.getNome());

        carregarTabela();
    }

    private void carregarTabela() {
        List<FichaTreino> todasFichaTreinos = buscarFichaTreinoUC.buscarPorAluno(usuarioLogado);
        fichasTreino.clear();
        fichasTreino.addAll(todasFichaTreinos);
    }


    public void selecionar(MouseEvent mouseEvent) {
        fichaTreinoSelecionada = tabelaFichaTreino.getSelectionModel().getSelectedItem();
    }

    public void telaDetalhesFichaTreino(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/DetalhesFichaTreinoUI");
    }

    public void registrarInicioTreino(ActionEvent actionEvent) {
    }

    public void registrarFinalTreino(ActionEvent actionEvent) {
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        this.usuarioLogado = null;
        WindowLoader.setRoot("AutenticacaoUI");
    }

    public void setUsuarioLogado(Usuario usuarioAutenticado) {
        if (usuarioAutenticado == null) {
            throw new IllegalArgumentException("Usuário Logado não pode ser nulo.");
        }
        this.usuarioLogado = usuarioAutenticado;
    }

    public void criarFichaTreino(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/GerenciarFichaTreinoUI");
    }


}
