package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.*;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.entities.ExercicioTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.exercicio.BuscarExercicioUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.BuscarExercicioTreinoUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.EditarExercicioTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.treino.BuscarTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public ComboBox<Exercicio> cbNomeExercicio;
    @FXML
    public TextField txtGrupoMuscularExercicio;

    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarFichaTreinoUC buscarFichaTreinoUC;
    private BuscarExercicioTreinoUC buscarExercicioTreinoUC;
    private BuscarExercicioUC buscarExercicioUC;
    private BuscarTreinoUC buscarTreinoUC;
    private EditarExercicioTreinoUC editarExercicioTreinoUC;
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
        editarExercicioTreinoUC = new EditarExercicioTreinoUC(new SqliteExercicioTreinoDAO());

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
                        && buscarExercicioTreinoUC.buscarPorId(dados.getIdAuxiliar2()).isPresent()) {
                    exercicioTreinoSelecionado = buscarExercicioTreinoUC.buscarPorId(dados.getIdAuxiliar2()).get();
                    carregarDadosDaEntidadeNaView();
                }
                cbNomeExercicio.getItems().setAll(buscarExercicioUC.buscarTodos());
            }
        });
    }

    private void carregarDadosDaEntidadeNaView() {
        cbNomeExercicio.setValue(exercicioTreinoSelecionado.getExercicio());
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
        WindowLoader.setRoot("aluno/DetalhesFichaTreinoUI", new Dados(usuarioAutenticado.getId(),
                                                                            alunoSelecionado.getId(),
                                                                            exercicioTreinoSelecionado.getTreino().getFichaTreino().getId()));
    }

    public void salvarExercicioTreino(ActionEvent actionEvent) throws IOException {
        carregarDadosDaViewNaEntidade();
        editarExercicioTreinoUC.atualizar(exercicioTreinoParaSalvar);
        voltarParaTelaAnterior(actionEvent);
    }

    private void carregarDadosDaViewNaEntidade() {
        exercicioTreinoParaSalvar = new ExercicioTreino();
        exercicioTreinoParaSalvar.setExercicio(cbNomeExercicio.getSelectionModel().getSelectedItem());
        exercicioTreinoParaSalvar.setTreino(exercicioTreinoSelecionado.getTreino());
        exercicioTreinoParaSalvar.setCarga(Double.valueOf(txtCargaExercicioTreino.getText()));
        exercicioTreinoParaSalvar.setRepeticao(Integer.valueOf(txtRepeticoesExercicioTreino.getText()));
        exercicioTreinoParaSalvar.setSerie(Integer.valueOf(txtSeriesExercicioTreino.getText()));
        exercicioTreinoParaSalvar.setId(exercicioTreinoSelecionado.getId());
    }
}
