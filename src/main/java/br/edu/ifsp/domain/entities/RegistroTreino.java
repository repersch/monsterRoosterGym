package br.edu.ifsp.domain.entities;

import java.time.LocalDate;

public class RegistroTreino {

    private Integer id;
    private LocalDate inicio;
    private LocalDate fim;
    private EstadoRegistroTreino estadoRegistroTreino;
    private Aluno aluno;

    public RegistroTreino() {
    }

    public RegistroTreino(Integer id, LocalDate inicio, LocalDate fim, EstadoRegistroTreino estadoRegistroTreino, Aluno aluno) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.estadoRegistroTreino = estadoRegistroTreino;
        this.aluno = aluno;
    }

    public RegistroTreino(LocalDate inicio, LocalDate fim, EstadoRegistroTreino estadoRegistroTreino, Aluno aluno) {
        this.inicio = inicio;
        this.fim = fim;
        this.estadoRegistroTreino = estadoRegistroTreino;
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

    public EstadoRegistroTreino getEstadoRegistroTreino() {
        return estadoRegistroTreino;
    }

    public void setEstadoRegistroTreino(EstadoRegistroTreino estadoRegistroTreino) {
        this.estadoRegistroTreino = estadoRegistroTreino;
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
                ", finalizado=" + estadoRegistroTreino +
                ", aluno=" + aluno +
                '}';
    }
}

