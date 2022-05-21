package br.edu.ifsp.domain.entities;

public class Treino {

    private Integer id;
    private String nome;
    private String observacao;
    private FichaTreino fichaTreino;


    public Treino() {
    }

    public Treino(String nome, String observacao, FichaTreino fichaTreino) {
        this.nome = nome;
        this.observacao = observacao;
        this.fichaTreino = fichaTreino;
    }

    public Treino(Integer id, String nome, String observacao, FichaTreino fichaTreino) {
        this.id = id;
        this.nome = nome;
        this.observacao = observacao;
        this.fichaTreino = fichaTreino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public FichaTreino getFichaTreino() {
        return fichaTreino;
    }

    public void setFichaTreino(FichaTreino fichaTreino) {
        this.fichaTreino = fichaTreino;
    }

    @Override
    public String toString() {
        return "\nTreino: [" +
                "ID = " + id +
                ", Nome = '" + nome + '\'' +
                ", Observação = '" + observacao + '\'' +
                ", ID Ficha Treino = " + fichaTreino.getId() +
                ']';
    }
}
