package br.edu.ifsp.domain.entities;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Treino ultimoTreinoRealizado;

    public Aluno(String nome, String cpf, String email, String telefone, String genero, LocalDate dataNascimento, Double peso, Double altura, String observacoes, Treino ultimoTreinoRealizado) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
        this.observacoes = observacoes;
        this.ultimoTreinoRealizado = ultimoTreinoRealizado;
    }
}
