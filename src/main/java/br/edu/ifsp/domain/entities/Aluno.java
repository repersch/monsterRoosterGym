package br.edu.ifsp.domain.entities;

import java.time.LocalDate;
import java.util.List;


public class Aluno {
    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String genero;
    private LocalDate dataNascimento;
    private Double peso;
    private Double altura;
    private String observacoes;
    private List<RegistroTreino> listaTreinos;
//    private Treino ultimoTreinoRealizado;

    public Aluno() {
    }

    public Aluno(String nome, String cpf, String email, String telefone, String genero, LocalDate dataNascimento, Double peso, Double altura, String observacoes) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
        this.observacoes = observacoes;
//        this.ultimoTreinoRealizado = ultimoTreinoRealizado;
    }


    public Aluno(Integer id, String nome, String cpf, String email, String telefone, String genero, LocalDate dataNascimento, Double peso, Double altura, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
        this.observacoes = observacoes;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", genero='" + genero + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", peso=" + peso +
                ", altura=" + altura +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
