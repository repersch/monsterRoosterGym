package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.*;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.exercicio.BuscarExercicioUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.BuscarExercicioTreinoUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.CriarExercicioTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.treino.BuscarTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdicionarExercicioTreinoUIController {

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

    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarFichaTreinoUC buscarFichaTreinoUC;
    private BuscarExercicioTreinoUC buscarExercicioTreinoUC;
    private BuscarExercicioUC buscarExercicioUC;
    private BuscarTreinoUC buscarTreinoUC;
    private CriarExercicioTreinoUC criarExercicioTreinoUC;
    private Usuario usuarioAutenticado;
    private Usuario alunoSelecionado;
    private Treino treinoSelecionado;
    private ExercicioTreino exercicioTreinoParaSalvar;


    @FXML
    public void initialize() {

        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
        buscarFichaTreinoUC = new BuscarFichaTreinoUC(new SqliteFichaTreinoDAO());
        buscarExercicioTreinoUC = new BuscarExercicioTreinoUC(new SqliteExercicioTreinoDAO());
        buscarExercicioUC = new BuscarExercicioUC(new SqliteExercicioDAO());
        buscarTreinoUC = new BuscarTreinoUC(new SqliteTreinoDAO());
        criarExercicioTreinoUC = new CriarExercicioTreinoUC(new SqliteExercicioTreinoDAO());

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
                        && buscarTreinoUC.buscarPorId(dados.getIdAuxiliar2()).isPresent()) {
                    treinoSelecionado = buscarTreinoUC.buscarPorId(dados.getIdAuxiliar2()).get();
                }
                cbNomeExercicio.getItems().setAll(buscarExercicioUC.buscarTodos());
            }
        });
    }

    public void voltarParaTelaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/DetalhesFichaTreinoUI", new Dados(usuarioAutenticado.getId(),
                                                                            alunoSelecionado.getId(),
                                                                            treinoSelecionado.getFichaTreino().getId()));
    }

    public void salvarExercicioTreino(ActionEvent actionEvent) throws IOException {
        carregarDadosDaViewNaEntidade();
        criarExercicioTreinoUC.salvar(exercicioTreinoParaSalvar);
        voltarParaTelaAnterior(actionEvent);
    }

    private void carregarDadosDaViewNaEntidade() {
        exercicioTreinoParaSalvar = new ExercicioTreino();
        exercicioTreinoParaSalvar.setExercicio(cbNomeExercicio.getSelectionModel().getSelectedItem());
        exercicioTreinoParaSalvar.setTreino(treinoSelecionado);
        exercicioTreinoParaSalvar.setCarga(Double.valueOf(txtCargaExercicioTreino.getText()));
        exercicioTreinoParaSalvar.setRepeticao(Integer.valueOf(txtRepeticoesExercicioTreino.getText()));
        exercicioTreinoParaSalvar.setSerie(Integer.valueOf(txtSeriesExercicioTreino.getText()));
    }
}
