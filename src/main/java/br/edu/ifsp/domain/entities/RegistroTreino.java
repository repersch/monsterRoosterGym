package br.edu.ifsp.domain.entities;

import java.time.LocalDate;

public class RegistroTreino {

    private Integer id;
    private LocalDate inicio;
    private LocalDate fim;
    private Boolean finalizado;
    private Aluno aluno;

    public RegistroTreino() {
    }

    public RegistroTreino(Integer id, LocalDate inicio, LocalDate fim, Boolean finalizado, Aluno aluno) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.finalizado = finalizado;
        this.aluno = aluno;
    }

    public RegistroTreino(LocalDate inicio, LocalDate fim, Boolean finalizado, Aluno aluno) {
        this.inicio = inicio;
        this.fim = fim;
        this.finalizado = finalizado;
        this.aluno = aluno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "RegistroTreino{" +
                "id=" + id +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", finalizado=" + finalizado +
                ", aluno=" + aluno +
                '}';
    }
}

