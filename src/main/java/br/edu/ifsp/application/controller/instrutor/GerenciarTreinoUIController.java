package br.edu.ifsp.application.controller.instrutor;

import br.edu.ifsp.application.repository.dao.SqliteFichaTreinoDAO;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class GerenciarTreinoUIController {
    @FXML
    public Button btnCancelar;
    @FXML
    public Button btnSalvarExercicio;

    private Usuario usuarioAutenticado;
    private Usuario alunoSelecionado;
    private FichaTreino fichaTreinoSelecionada;

    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarFichaTreinoUC buscarFichaTreinoUC;

    @FXML
    public void initialize() {
        buscarFichaTreinoUC = new BuscarFichaTreinoUC(new SqliteFichaTreinoDAO());
        buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());

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
            }
        });
    }

    public void voltarParaCenaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/DetalhesFichaTreinoUI", new Dados(usuarioAutenticado.getId(),
                alunoSelecionado.getId(),
                fichaTreinoSelecionada.getId()));
    }

    public void salvarTreino(ActionEvent actionEvent) {
    }
}
