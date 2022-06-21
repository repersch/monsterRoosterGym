package br.edu.ifsp.domain.usecases.treino;

import br.edu.ifsp.application.repository.dao.SqliteFichaTreinoDAO;
import br.edu.ifsp.application.repository.dao.SqliteTreinoDAO;
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.FichaTreinoDAO;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import javafx.collections.FXCollections;

import java.util.List;

public class GerarRelatorioTreinoAluno {
    private Usuario aluno;
    private BuscarTreinoUC buscarTreinoUC;

    public GerarRelatorioTreinoAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public boolean buscarFichasAluno(List<FichaTreino> listaFichasTreino){
        buscarTreinoUC = new BuscarTreinoUC(new SqliteTreinoDAO());
        for(int i = 0; i < listaFichasTreino.size(); i++) {
            List<Treino> listaTreinos = buscarTreinoUC.buscarTreinosPorFichaTreino(listaFichasTreino.get(i));
            System.out.println(listaFichasTreino.get(i) + "\n" + listaTreinos);
        }
        return true;
    }

    @Override
    public String toString() {
        return "GerarRelatorioTreinoAluno{" +
                "aluno=" + aluno +
                '}';
    }
}