package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.application.repository.dao.SqliteExercicioDAO;
import br.edu.ifsp.domain.entities.Exercicio;
import br.edu.ifsp.domain.entities.GrupoMuscular;
import br.edu.ifsp.domain.usecases.exercicio.BuscarExercicioUC;
import br.edu.ifsp.domain.usecases.exercicio.CriarExercicioUC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

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

    private Exercicio exercicio;

    BuscarExercicioUC buscarExercicioUC;
    CriarExercicioUC criarExercicioUC = new CriarExercicioUC(new SqliteExercicioDAO());

    @FXML
    private void initialize() {
        cbGrupoMuscular.getItems().setAll(GrupoMuscular.values());
    }

    private void carregarDadosDaViewNaEntidade() {
        if (exercicio == null) {
            exercicio = new Exercicio();
        }

        exercicio.setNome(txtNomeExercicio.getText());
        exercicio.setGrupoMuscular(cbGrupoMuscular.getValue());
        exercicio.setDescricao(txtDescricaoExercicio.getText());
    }


    public void voltarParaCenaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("TabelaExercicioUI");
    }

    public void salvarExercicio(ActionEvent actionEvent) throws IOException {
        carregarDadosDaViewNaEntidade();
        criarExercicioUC.salvar(exercicio);
        voltarParaCenaAnterior(actionEvent);
    }

    public void carregarDadosDaEntidadeNaView(Exercicio exercicioSelecionado) {
        if (exercicioSelecionado == null) {
            throw  new IllegalArgumentException("Exercício não pode ser nulo.");
        }

        this.exercicio = exercicioSelecionado;
        txtNomeExercicio.setText(exercicio.getNome());
        txtDescricaoExercicio.setText(exercicio.getDescricao());
        cbGrupoMuscular.setValue(exercicio.getGrupoMuscular());
    }


}
