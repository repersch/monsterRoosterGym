package br.edu.ifsp.domain.usecases.treino;
git
import br.edu.ifsp.domain.entities.FichaTreino;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;

public class GerarRelatorioTreinoAluno {
    private Usuario aluno;
    private FichaTreino ficha;

    public GerarRelatorioTreinoAluno(Usuario aluno, FichaTreino ficha) {
        this.aluno = aluno;
        this.ficha = ficha;
    }

    public boolean buscarAluno(BuscarUsuarioUC buscarUsuarioUC, Integer num){
        buscarUsuarioUC.buscarPorId(num);
        return true;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public FichaTreino getFicha() {
        return ficha;
    }

    public void setFicha(FichaTreino ficha) {
        this.ficha = ficha;
    }

    @Override
    public String toString() {
        return "GerarRelatorioTreinoAluno{" +
                "aluno=" + aluno +
                ", ficha=" + ficha +
                '}';
    }
}
