package br.edu.ifsp.domain.entities;

import java.time.LocalDate;

public class RegistroTreino {

    private Integer id;
    private LocalDate inicio;
    private LocalDate fim;
    private EstadoRegistroTreino estadoRegistroTreino;
    private Usuario usuario;

    public RegistroTreino() {
    }

    public RegistroTreino(Integer id, LocalDate inicio, LocalDate fim, EstadoRegistroTreino estadoRegistroTreino, Usuario usuario) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.estadoRegistroTreino = estadoRegistroTreino;
        this.usuario = usuario;
    }

    public RegistroTreino(LocalDate inicio, LocalDate fim, EstadoRegistroTreino estadoRegistroTreino, Usuario usuario) {
        this.inicio = inicio;
        this.fim = fim;
        this.estadoRegistroTreino = estadoRegistroTreino;
        this.usuario = usuario;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
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

    @Override
    public String toString() {
        return "\nRegistroTreino{" +
                "id=" + id +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", finalizado=" + estadoRegistroTreino +
                ", aluno=" + usuario +
                '}';
    }
}

