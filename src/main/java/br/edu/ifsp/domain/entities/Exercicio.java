package br.edu.ifsp.domain.entities;


public class Exercicio {
    private Integer id;
    private String nome;
    private GrupoMuscular grupoMuscular;
    private String descricao;
    private Boolean emUso;

    public Exercicio() {
    }

    public Exercicio(String nome, GrupoMuscular grupoMuscular, String descricao, Boolean emUso) {
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.descricao = descricao;
        this.emUso = emUso;
    }

    public Exercicio(Integer id, String nome, GrupoMuscular grupoMuscular, String descricao, Boolean emUso) {
        this.id = id;
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.descricao = descricao;
        this.emUso = emUso;
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

    public GrupoMuscular getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getEmUso() {
        return emUso;
    }

    public void setEmUso(Boolean emUso) {
        this.emUso = emUso;
    }

    @Override
    public String toString() {
        return "\nExercicio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", grupoMuscular=" + grupoMuscular +
                ", descricao='" + descricao + '\'' +
                ", emUso=" + emUso +
                '}';
    }
}
