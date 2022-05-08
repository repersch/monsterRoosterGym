package br.edu.ifsp.domain.entities;

public class Instrutor {
    private Integer id;
    private String nome;

    public Instrutor() {
    }

    public Instrutor(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Instrutor(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Instrutor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
