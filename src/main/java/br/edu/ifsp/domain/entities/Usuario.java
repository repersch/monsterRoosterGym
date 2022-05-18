package br.edu.ifsp.domain.entities;

public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Boolean isInstrutor;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, String senha, Boolean isInstrutor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isInstrutor = isInstrutor;
    }

    public Usuario(String nome, String email, String senha, Boolean isInstrutor) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isInstrutor = isInstrutor;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getInstrutor() {
        return isInstrutor;
    }

    public void setInstrutor(Boolean instrutor) {
        isInstrutor = instrutor;
    }

    @Override
    public String toString() {
        return "\nUsuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", isInstrutor=" + isInstrutor +
                '}';
    }
}
