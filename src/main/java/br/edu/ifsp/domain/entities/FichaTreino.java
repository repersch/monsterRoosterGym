package br.edu.ifsp.domain.entities;

import java.time.LocalDate;

public class FichaTreino {
    private Integer id;
    private Boolean valido;
    private LocalDate dataInicio;
    private LocalDate validade;
    private Usuario aluno;
    private Usuario instrutor;
    //private Treino treino;

    public FichaTreino() {
    }

    public FichaTreino(Integer id, Boolean valido, LocalDate dataInicio, LocalDate validade, Usuario aluno, Usuario instrutor) {
        this.id = id;
        this.valido = valido;
        this.dataInicio = dataInicio;
        this.validade = validade;
        this.aluno = aluno;
        this.instrutor = instrutor;
    }

    public FichaTreino(Boolean valido, LocalDate dataInicio, LocalDate validade, Usuario aluno, Usuario instrutor) {
        this.valido = valido;
        this.dataInicio = dataInicio;
        this.validade = validade;
        this.aluno = aluno;
        this.instrutor = instrutor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getValido() {
        return valido;
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Usuario getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Usuario instrutor) {
        this.instrutor = instrutor;
    }

    @Override
    public String toString() {
        return "\nFichaTreino{" +
                "id=" + id +
                ", valido=" + valido +
                ", dataInicio=" + dataInicio +
                ", validade=" + validade +
                ", aluno=" + aluno +
                ", instrutor=" + instrutor +
                '}';
    }
}
