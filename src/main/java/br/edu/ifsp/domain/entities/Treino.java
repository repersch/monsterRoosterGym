package br.edu.ifsp.domain.entities;

import java.util.List;

public class Treino {
    private Integer id;
    private String nome;
    private String observacao;
    private List<ExercicioTreino> exerciciosTreino;

    public Treino(Integer id, String nome, String observacao, List<ExercicioTreino> exerciciosTreino) {
        this.id = id;
        this.nome = nome;
        this.observacao = observacao;
        this.exerciciosTreino = exerciciosTreino;
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

    public List<ExercicioTreino> getExerciciosTreino() {
        return exerciciosTreino;
    }

    public void setExerciciosTreino(List<ExercicioTreino> exerciciosTreino) {
        this.exerciciosTreino = exerciciosTreino;
    }

    @Override
    public String toString() {
        return "Treino{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", observacao='" + getObservacao() + '\'' +
                ", exerciciosTreino=" + getExerciciosTreino() +
                '}';
    }
}
