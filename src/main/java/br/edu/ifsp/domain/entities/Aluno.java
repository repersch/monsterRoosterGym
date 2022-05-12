package br.edu.ifsp.domain.entities;

import java.time.LocalDate;

public class Aluno extends Usuario {

    private String cpf;
    private String telefone;
    private String genero;
    private LocalDate dataNascimento;
    private Double peso;
    private Double altura;
    private String observacoes;
    private Treino ultimoTreinoRealizado;

    public Aluno() {
    }

    public Aluno(Integer id, String nome, String email, String senha, Boolean isInstrutor, String cpf, String telefone, String genero, LocalDate dataNascimento, Double peso, Double altura, String observacoes) {
        super(id, nome, email, senha, isInstrutor);
        this.cpf = cpf;
        this.telefone = telefone;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
        this.observacoes = observacoes;
//        this.ultimoTreinoRealizado = ultimoTreinoRealizado;
    }

    public Aluno(String nome, String email, String senha, Boolean isInstrutor, String cpf, String telefone, String genero, LocalDate dataNascimento, Double peso, Double altura, String observacoes) {
        super(nome, email, senha, isInstrutor);
        this.cpf = cpf;
        this.telefone = telefone;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
        this.observacoes = observacoes;
//        this.ultimoTreinoRealizado = ultimoTreinoRealizado;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Treino getUltimoTreinoRealizado() {
        return ultimoTreinoRealizado;
    }

    public void setUltimoTreinoRealizado(Treino ultimoTreinoRealizado) {
        this.ultimoTreinoRealizado = ultimoTreinoRealizado;
    }

    @Override
    public String toString() {
        return "\n\nAluno{" +
                "\ncpf='" + cpf + '\'' +
                ", \ntelefone='" + telefone + '\'' +
                ", \ngenero='" + genero + '\'' +
                ", \ndataNascimento=" + dataNascimento +
                ", \npeso=" + peso +
                ", \naltura=" + altura +
                ", \nobservacoes='" + observacoes + '\'' +
                ", \nultimoTreinoRealizado=" + ultimoTreinoRealizado +
                '}';
    }
}
