package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.*;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.exercicio.BuscarExercicioUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.BuscarExercicioTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.treino.BuscarTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GerenciarExercicioTreinoUIController {
    @FXML
    public TextField txtDescricaoExercicio;
    @FXML
    public TextField txtSeriesExercicioTreino;
    @FXML
    public TextField txtCargaExercicioTreino;
    @FXML
    public TextField txtRepeticoesExercicioTreino;
    @FXML
    public Button btnCancelar;
    @FXML
    public Button btnCriarFichaTreino;
    @FXML
    public ComboBox cbNomeExercicio;
    @FXML
    public TextField txtGrupoMuscularExercicio;

    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarFichaTreinoUC buscarFichaTreinoUC;
    private BuscarExercicioTreinoUC buscarExercicioTreinoUC;
    private BuscarExercicioUC buscarExercicioUC;
    private BuscarTreinoUC buscarTreinoUC;
    private Usuario usuarioAutenticado;
    private Usuario alunoSelecionado;
    private ExercicioTreino exercicioTreinoSelecionado;
    private ExercicioTreino exercicioTreinoParaSalvar;




    @FXML
    public void initialize() {

        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
        buscarFichaTreinoUC = new BuscarFichaTreinoUC(new SqliteFichaTreinoDAO());
        buscarExercicioTreinoUC = new BuscarExercicioTreinoUC(new SqliteExercicioTreinoDAO());
        buscarExercicioUC = new BuscarExercicioUC(new SqliteExercicioDAO());
        buscarTreinoUC = new BuscarTreinoUC(new SqliteTreinoDAO());

        WindowLoader.addOnChangeScreenListener(new WindowLoader.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Dados dados) {

                if (dados.getIdUsuarioAutenticado() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();

                    if (!usuarioAutenticado.getInstrutor()) {
                        cbNomeExercicio.setDisable(true);
                        txtSeriesExercicioTreino.setDisable(true);
                        txtRepeticoesExercicioTreino.setDisable(true);
                    }
                }
                if (dados.getIdAuxiliar() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).isPresent()) {
                    alunoSelecionado = buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).get();
                }
                if (dados.getIdAuxiliar2() > 0
                        && buscarFichaTreinoUC.buscarPorId(dados.getIdAuxiliar2()).isPresent()) {
                    exercicioTreinoSelecionado = buscarExercicioTreinoUC.buscarPorId(dados.getIdAuxiliar2()).get();
                    carregarDadosDaEntidadeNaView();
                }
                cbNomeExercicio.getItems().setAll(buscarExercicioUC.buscarTodos());
            }
        });
    }

    private void carregarDadosDaEntidadeNaView() {
        cbNomeExercicio.setValue(exercicioTreinoSelecionado.getExercicio().getNome());
        txtGrupoMuscularExercicio.setText(exercicioTreinoSelecionado.getExercicio().getGrupoMuscular().getMusculo());
        txtGrupoMuscularExercicio.setEditable(false);
        txtGrupoMuscularExercicio.setDisable(true);
        txtDescricaoExercicio.setText(exercicioTreinoSelecionado.getExercicio().getDescricao());
        txtDescricaoExercicio.setEditable(false);
        txtDescricaoExercicio.setDisable(true);
        txtSeriesExercicioTreino.setText(String.valueOf(exercicioTreinoSelecionado.getSerie()));
        txtCargaExercicioTreino.setText(String.valueOf(exercicioTreinoSelecionado.getCarga()));
        txtRepeticoesExercicioTreino.setText(String.valueOf(exercicioTreinoSelecionado.getRepeticao()));
    }


    public void voltarParaTelaAnterior(ActionEvent actionEvent) throws IOException {
        // quando chama DetalhesFichaTreinoUI precisa enviar o id da ficha treino
        Integer idFichaTreino = 0;
        if (buscarTreinoUC.buscarPorId(exercicioTreinoSelecionado.getTreino().getId()).isPresent()) {
            Treino treino = buscarTreinoUC.buscarPorId(exercicioTreinoSelecionado.getTreino().getId()).get();
            idFichaTreino = treino.getFichaTreino().getId();
        }

        WindowLoader.setRoot("aluno/DetalhesFichaTreinoUI", new Dados(usuarioAutenticado.getId(),
                                                                            alunoSelecionado.getId(),
                                                                            idFichaTreino));
    }

    public void salvarExercicioTreino(ActionEvent actionEvent) {
        carregarDadosDaViewNaEntidade();
    }

    private void carregarDadosDaViewNaEntidade() {
        exercicioTreinoParaSalvar = new ExercicioTreino();
        exercicioTreinoParaSalvar.setExercicio((Exercicio) cbNomeExercicio.getSelectionModel().getSelectedItem());

    }


    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
