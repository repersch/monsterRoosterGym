package br.edu.ifsp.application.controller.aluno;

import br.edu.ifsp.application.repository.dao.SqliteFichaTreinoDAO;
import br.edu.ifsp.application.repository.dao.SqliteTreinoDAO;
import br.edu.ifsp.application.repository.dao.SqliteUsuarioDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.Dados;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.treino.BuscarTreinoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

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
    public ListView<String> treinoListView;

    private Usuario usuarioAutenticado;
    private Usuario usuarioSelecionado;
    private FichaTreino fichaTreinoSelecionada;
    private ObservableList<Treino> treinos;

    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarFichaTreinoUC buscarFichaTreinoUC;
    private BuscarTreinoUC buscarTreinoUC;


    @FXML
    public void initialize() {

        WindowLoader.addOnChangeScreenListener(new WindowLoader.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Dados dados) {

                buscarFichaTreinoUC = new BuscarFichaTreinoUC(new SqliteFichaTreinoDAO());
                buscarUsuarioUC = new BuscarUsuarioUC(new SqliteUsuarioDAO());
                buscarTreinoUC = new BuscarTreinoUC(new SqliteTreinoDAO());

                if (dados.getIdUsuarioAutenticado() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).isPresent()) {
                    usuarioAutenticado = buscarUsuarioUC.buscarPorId(dados.getIdUsuarioAutenticado()).get();
                }
                if (dados.getIdAuxiliar() > 0
                        && buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).isPresent()) {
                    usuarioSelecionado = buscarUsuarioUC.buscarPorId(dados.getIdAuxiliar()).get();
                }
                if (dados.getIdAuxiliar2() > 0
                        && buscarFichaTreinoUC.buscarPorId(dados.getIdAuxiliar2()).isPresent()) {
                    fichaTreinoSelecionada = buscarFichaTreinoUC.buscarPorId(dados.getIdAuxiliar2()).get();
                }

                treinos = FXCollections.observableArrayList(buscarTreinoUC.buscarTreinosPorFichaTreino(fichaTreinoSelecionada));
//                treinoListView.setItems(treinos.);

                txtAlunoLogado.setText(usuarioAutenticado.getNome());
            }
        });
    }


    public void telaDetalhesFichaTreino(ActionEvent actionEvent) {
    }

    public void fazerLogOut(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AutenticacaoUI", new Dados(0, 0, 0));
    }

    public void voltarParaTelaAnterior(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("aluno/TabelaFichaTreinoUI", new Dados(usuarioAutenticado.getId(), usuarioSelecionado.getId(), fichaTreinoSelecionada.getId()));
    }
}
