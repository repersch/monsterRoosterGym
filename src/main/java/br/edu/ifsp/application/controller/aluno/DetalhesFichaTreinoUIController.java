package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.*;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.exercicio.BuscarExercicioUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.BuscarExercicioTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.treino.BuscarTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class DetalhesFichaTreinoUIController {

    @FXML
    public Label txtAlunoLogado;
    @FXML
    public Button btnDetalhesFichaTreino;
    @FXML
    public Button btnLogOut;
    @FXML
    public Button btnCancelar;
    @FXML
    public Label txtIdFichaTreino;
    @FXML
    public Label txtIdTreino;
    @FXML
    public Label txtNomeTreino;
    @FXML
    public Label txtObservacaoTreino;
    @FXML
    public TableView<Treino> tableViewTreino;
    @FXML
    public TableColumn<Object, Object> cTreino;
    @FXML
    public Button btnAdicionarExercicio;
    @FXML
    public Button btnAdicionarTreino;
    @FXML
    public TableColumn cExercicio;
    @FXML
    public TableColumn cGrupoMuscular;
    @FXML
    public TableView<ExercicioTreino> tableViewExercicios;

    private Usuario usuarioAutenticado;
    private Usuario alunoSelecionado;
    private FichaTreino fichaTreinoSelecionada;
    private Treino treinoSelecionado;
    private ObservableList<Treino> treinos;
    private ObservableList<ExercicioTreino> exerciciosTreino;

    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarFichaTreinoUC buscarFichaTreinoUC;
    private BuscarTreinoUC buscarTreinoUC;
    private BuscarExercicioTreinoUC buscarExercicioTreinoUC;
    private BuscarExercicioUC buscarExercicioUC;


    @FXML
    public void initialize() {

        buscarFichaTreinoUC = new BuscarFichaTreinoUC(new SqliteFichaTreinoDAO());
        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
        buscarTreinoUC = new BuscarTreinoUC(new SqliteTreinoDAO());
        buscarExercicioTreinoUC = new BuscarExercicioTreinoUC(new SqliteExercicioTreinoDAO());
        buscarExercicioUC = new BuscarExercicioUC(new SqliteExercicioDAO());

        WindowLoader.addOnChangeScreenListener(new WindowLoader.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Dados dados) {

                if (dados.getIdUsuarioAutenticado() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();
                }
                if (dados.getIdAuxiliar() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).isPresent()) {
                    alunoSelecionado = buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).get();
                }
                if (dados.getIdAuxiliar2() > 0
                        && buscarFichaTreinoUC.buscarPorId(dados.getIdAuxiliar2()).isPresent()) {
                    fichaTreinoSelecionada = buscarFichaTreinoUC.buscarPorId(dados.getIdAuxiliar2()).get();
                }

                treinos = FXCollections.observableArrayList(buscarTreinoUC.buscarTreinosPorFichaTreino(fichaTreinoSelecionada));
                tableViewTreino.setItems(treinos);
                cTreino.setCellValueFactory(new PropertyValueFactory<>("nome"));

                txtIdFichaTreino.setText("FICHA TREINO " + fichaTreinoSelecionada.getId());
                txtAlunoLogado.setText(usuarioAutenticado.getNome());
            }
        });
    }



    public void telaDetalhesFichaTreino(ActionEvent actionEvent) throws IOException {
        ExercicioTreino exercicioTreinoSelecionado = tableViewExercicios.getSelectionModel().getSelectedItem();
        if (exercicioTreinoSelecionado == null) {
            showAlert("Erro!", "Selecione um exercício.", Alert.AlertType.ERROR);
            return;
        }

        WindowLoader.setRoot("aluno/GerenciarExercicioTreinoUi", new Dados(usuarioAutenticado.getId(),
                                                                                 alunoSelecionado.getId(),
                                                                                 exercicioTreinoSelecionado.getId()));
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AutenticacaoUI", new Dados(0, 0, 0));
    }

    public void voltarParaTelaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/TabelaFichaTreinoUI", new Dados(usuarioAutenticado.getId(), alunoSelecionado.getId(), fichaTreinoSelecionada.getId()));
    }

    public void adicionarExercicioNoTreino(ActionEvent actionEvent) {
    }

    public void adicionarTreinoNaFichaTreino(ActionEvent actionEvent) {
    }

    public void selecionarTreino(MouseEvent mouseEvent) {
        treinoSelecionado = tableViewTreino.getSelectionModel().getSelectedItem();
        if (treinoSelecionado == null) {
            showAlert("Erro!", "Selecione um treino.", Alert.AlertType.ERROR);
            return;
        }

        txtIdTreino.setText(String.valueOf(treinoSelecionado.getId()));
        txtNomeTreino.setText(treinoSelecionado.getNome());
        txtObservacaoTreino.setText(treinoSelecionado.getObservacao());
        exerciciosTreino = FXCollections.observableArrayList(buscarExercicioTreinoUC.buscarExerciciosTreinoPorTreino(treinoSelecionado.getId()));
        System.out.println(exerciciosTreino);
        tableViewExercicios.setItems(exerciciosTreino);

        // precisa fazer aparecer o nome e o grupo muscular nas células da tabela
        cExercicio.setCellValueFactory(new PropertyValueFactory<>("exercicio"));
        cGrupoMuscular.setCellValueFactory(new PropertyValueFactory<>("exercicio"));
    }


    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
