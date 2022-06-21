package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.application.repository.dao.SqliteExercicioDAO;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.entities.GrupoMuscular;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.exercicio.BuscarExercicioUC;
import br.edu.ifsp.domain.usecases.exercicio.CriarExercicioUC;
import br.edu.ifsp.domain.usecases.exercicio.EditarExercicioUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GerenciarExercicioUIController {
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvarExercicio;
    @FXML
    private TextField txtNomeExercicio;
    @FXML
    private ComboBox<GrupoMuscular> cbGrupoMuscular;
    @FXML
    private TextArea txtDescricaoExercicio;

    private Exercicio exercicioParaSalvar;
    private Usuario usuarioAutenticado;
    private Exercicio exercicioSelecionado;
    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarExercicioUC buscarExercicioUC;

    @FXML
    protected void initialize() {

        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
        buscarExercicioUC = new BuscarExercicioUC(new SqliteExercicioDAO());

        WindowLoader.addOnChangeScreenListener(new WindowLoader.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Dados dados) {

                if (dados.getIdUsuarioAutenticado() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();
                }

                if (dados.getIdAuxiliar() > 0
                        && buscarExercicioUC.buscarPorId(dados.getIdAuxiliar()).isPresent()) {
                    exercicioSelecionado = buscarExercicioUC.buscarPorId(dados.getIdAuxiliar()).get();
                    carregarDadosDaEntidadeNaView();
                }

                cbGrupoMuscular.getItems().setAll(GrupoMuscular.values());
            }
        });
    }

    private void carregarDadosDaViewNaEntidade() {
        if (exercicioParaSalvar == null) {
            exercicioParaSalvar = new Exercicio();
        }
        exercicioParaSalvar.setNome(txtNomeExercicio.getText());
        exercicioParaSalvar.setGrupoMuscular(cbGrupoMuscular.getValue());
        exercicioParaSalvar.setDescricao(txtDescricaoExercicio.getText());
    }


    public void voltarParaCenaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("TabelaExercicioUI", new Dados(usuarioAutenticado.getId(), 0, 0));
    }

    public void salvarExercicio(ActionEvent actionEvent) throws IOException {
        carregarDadosDaViewNaEntidade();

        if (exercicioSelecionado == null) {
            CriarExercicioUC criarExercicioUC = new CriarExercicioUC(new SqliteExercicioDAO());
            exercicioParaSalvar.setEmUso(false);
            criarExercicioUC.salvar(exercicioParaSalvar);
        } else {
            EditarExercicioUC editarExercicioUC = new EditarExercicioUC(new SqliteExercicioDAO());
            exercicioParaSalvar.setId(exercicioSelecionado.getId());
            exercicioParaSalvar.setEmUso(exercicioSelecionado.getEmUso());
            editarExercicioUC.atualizar(exercicioParaSalvar);
        }
        voltarParaCenaAnterior(actionEvent);
    }

    public void carregarDadosDaEntidadeNaView() {
        if (exercicioSelecionado == null) {
            throw  new IllegalArgumentException("Exercício não pode ser nulo.");
        }
        txtNomeExercicio.setText(exercicioSelecionado.getNome());
        txtDescricaoExercicio.setText(exercicioSelecionado.getDescricao());
        cbGrupoMuscular.setValue(exercicioSelecionado.getGrupoMuscular());
    }
}
