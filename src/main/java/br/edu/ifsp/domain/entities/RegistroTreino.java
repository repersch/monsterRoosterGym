package br.edu.ifsp.domain.entities;

import java.time.LocalDateTime;

public class RegistroTreino {

    private Integer id;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private EstadoRegistroTreino estadoRegistroTreino;
    private Usuario usuario;
    private Treino treino;

    public RegistroTreino() {
    }

    public RegistroTreino(Integer id, LocalDateTime inicio, LocalDateTime fim, EstadoRegistroTreino estadoRegistroTreino, Usuario usuario, Treino treino) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.estadoRegistroTreino = estadoRegistroTreino;
        this.usuario = usuario;
        this.treino = treino;
    }

    public RegistroTreino(LocalDateTime inicio, LocalDateTime fim, EstadoRegistroTreino estadoRegistroTreino, Usuario usuario, Treino treino) {
        this.inicio = inicio;
        this.fim = fim;
        this.estadoRegistroTreino = estadoRegistroTreino;
        this.usuario = usuario;
        this.treino = treino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public EstadoRegistroTreino getEstadoRegistroTreino() {
        return estadoRegistroTreino;
    }

    public void setEstadoRegistroTreino(EstadoRegistroTreino estadoRegistroTreino) {
        this.estadoRegistroTreino = estadoRegistroTreino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    @Override
    public String toString() {
        return "\nRegistroTreino: [" +
                "ID = " + id +
                ", Inicio = " + inicio +
                ", Fim = " + fim +
                ", EstadoRegistroTreino = " + estadoRegistroTreino +
                ", Nome do aluno = " + usuario.getNome() +
                ", Nome do treino = " + treino.getNome() +
                ']';
    }
}

