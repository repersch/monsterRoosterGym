package br.edu.ifsp.domain.entities;

public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Boolean isInstrutor;
    private Aluno aluno;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, String senha, Boolean isInstrutor, Aluno aluno) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isInstrutor = isInstrutor;
        this.aluno = aluno;
    }

    public Usuario(String nome, String email, String senha, Boolean isInstrutor, Aluno aluno) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isInstrutor = isInstrutor;
        this.aluno = aluno;
    }

    public int getId() {
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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "\nUsuário: [" +
                "ID = " + id +
                ", Nome = '" + nome + '\'' +
                ", E-mail = '" + email + '\'' +
                ", Senha = '" + senha + '\'' +
                ", É Instrutor = " + isInstrutor +
                ", Aluno = " + ((aluno != null) ? aluno.toString() : null) +
                ']';
    }
}
