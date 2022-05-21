package br.edu.ifsp.domain.entities;

import java.time.LocalDate;

public class Aluno {

    private String cpf;
    private String telefone;
    private String genero;
    private LocalDate dataNascimento;
    private Double peso;
    private Double altura;
    private String observacoes;

    public Aluno() {
    }

    public Aluno(String cpf, String telefone, String genero, LocalDate dataNascimento, Double peso, Double altura, String observacoes) {
        this.cpf = cpf;
        this.telefone = telefone;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
        this.observacoes = observacoes;
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


    @Override
    public String toString() {
        return "Aluno{" +
                "cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", genero='" + genero + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", peso=" + peso +
                ", altura=" + altura +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
