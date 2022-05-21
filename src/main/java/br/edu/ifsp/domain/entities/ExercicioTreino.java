package br.edu.ifsp.domain.entities;

public class ExercicioTreino {

    private Integer id;
    private Integer serie;
    private Double carga;
    private Integer repeticao;
    private Treino treino;
    private Exercicio exercicio;

    public ExercicioTreino() {
    }

    public ExercicioTreino(Integer serie, Double carga, Integer repeticao, Treino treino, Exercicio exercicio) {
        this.serie = serie;
        this.carga = carga;
        this.repeticao = repeticao;
        this.treino = treino;
        this.exercicio = exercicio;
    }

    public ExercicioTreino(Integer id, Integer serie, Double carga, Integer repeticao, Treino treino, Exercicio exercicio) {
        this.id = id;
        this.serie = serie;
        this.carga = carga;
        this.repeticao = repeticao;
        this.treino = treino;
        this.exercicio = exercicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Double getCarga() {
        return carga;
    }

    public void setCarga(Double carga) {
        this.carga = carga;
    }

    public Integer getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(Integer repeticao) {
        this.repeticao = repeticao;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    @Override
    public String toString() {
        return "\n\nExercicioTreino: [" +
                "ID = " + id +
                ", Série = " + serie +
                ", Carga = " + carga +
                ", Repetições = " + repeticao +
                ", Treino = " + treino +
                ", Exercício = " + exercicio +
                ']';
    }
}
